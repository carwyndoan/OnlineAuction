package miu.edu.auction.service.impl;

import miu.edu.auction.domain.Bidding;
import miu.edu.auction.domain.PayPalData;
import miu.edu.auction.domain.Payment;
import miu.edu.auction.domain.User;
import miu.edu.auction.dto.InvoiceDTO;
import miu.edu.auction.repository.PaymentRepository;
import miu.edu.auction.repository.PaypalDataRepository;
import miu.edu.auction.service.BiddingService;
import miu.edu.auction.service.PaymentService;
import miu.edu.auction.service.PaypalService;
import miu.edu.auction.utils.CommonUtils;
import miu.edu.auction.utils.GenerationUnique;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired // or @Inject
    private JobScheduler jobScheduler;

    @Autowired // or @Inject
    private BiddingService biddingService;

    @Autowired
    PaypalDataRepository paypalDataRepository;

    @Autowired
    PaypalService paypalService;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment makePayment(Payment payment) {
        //TODO: call Paypal service here
        //After 3 days, if Bidding status is still 2 -> return full payement for Winner
        Bidding bidding = payment.getBiddingPayment();
        jobScheduler.schedule(() -> biddingService.returnBidderDeposit(bidding.getBidding_id()),
                LocalDateTime.now().plusSeconds(CommonUtils.calculateDuration(payment.getPaymentDate().plusDays(3))));//Return full to bidder after 3 days
//        jobScheduler.schedule(() -> biddingService.returnBidderDeposit(bidding.getBidding_id()),
//                LocalDateTime.now().plusSeconds(CommonUtils.calculateDuration(payment.getPaymentDate().plusMinutes(1))));//1 minutes for testing purpose
        return savePayment(payment);
    }

    @Override
    public Payment findPaymentByBiddingIDAndUser(Integer biddingID, Integer userID) {
        return paymentRepository.findPaymentByBiddingIDAndUser(biddingID, userID);
    }

    @Override
    public Payment chargeDeposit(Payment payment) {
        //TODO: payment with paypal
        payment.setDepositDate(LocalDateTime.now());
        paymentRepository.save(payment);
        PayPalData payPalData = paypalDataRepository.findPayPalDataByBiddingId(payment.getBiddingPayment().getBidding_id()).stream()
                .filter(p -> p.getLocal_payment_id() == null)
                .findFirst().get();
        payPalData.setLocal_payment_id(payment.getPayment_id());
        paypalDataRepository.save(payPalData);
        return  savePayment(payment);
    }

    @Override
    public Payment returnDeposit(Payment payment) {
        //TODO: payment with paypal
        //Update system
        payment.setReturnDeposit(payment.getDeposit());
        payment.setReturnDepositDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment payToSeller(Payment payment) {
        //TODO: payment with paypal
        //Update system
        payment.setPaySellerDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return  payment;
    }

    @Override
    public InvoiceDTO makeInvoice(Integer biddingID, Integer userID) {
        Payment payment = paymentRepository.findPaymentByBiddingIDAndUser(biddingID, userID);
        InvoiceDTO dto = new InvoiceDTO();
        dto.setShipping_Name(payment.getReceiverName());
        dto.setShipping_State(payment.getState());
        dto.setShipping_Street(payment.getStreet());
        dto.setShipping_City(payment.getCity());
        dto.setShipping_ZipCode(payment.getZipcode());
        dto.setShipping_Date(payment.getShipDate());
        dto.setDeliveredDate(payment.getDeliveryDate());
        dto.setPayment_DepositAmount(payment.getDeposit());
        dto.setPayment_DepositDate(payment.getDepositDate());
        dto.setPayment_DepositDate(payment.getDepositDate());
        dto.setPayment_RemainingAmount(payment.getRemainingAmount());
        dto.setPayment_Date(payment.getPaymentDate());
        dto.setTotal(payment.getDeposit() + payment.getRemainingAmount());

        dto.setOrder_Name(payment.getUser_payment().getName());
        dto.setOrder_Street(payment.getUser_payment().getStreet());
        dto.setOrder_City(payment.getUser_payment().getCity());
        dto.setOrder_State(payment.getUser_payment().getState());
        dto.setOrder_ZipCode(payment.getUser_payment().getZipcode());
        dto.setOrder_Email(payment.getUser_payment().getEmail());

        dto.setProduct_Name(payment.getBiddingPayment().getProduct().getName());
        dto.setProduct_Price(payment.getBiddingPayment().getFinalprice());
        dto.setProduct_VendorName(payment.getBiddingPayment().getProduct().getUser().getName());
        dto.setProduct_Description(payment.getBiddingPayment().getProduct().getDescription().substring(0, 20));
        dto.setProduct_Quantity(1);
        dto.setInvoice_CreatedDate(LocalDate.now());

        if (payment.getInvoiceNumber() == null) {
            String invoiceNumber = GenerationUnique.generateInvoiceNumber();
            dto.setInvoice_Number(invoiceNumber);
            payment.setInvoiceNumber(invoiceNumber);
            paymentRepository.save(payment);
        } else {
            dto.setInvoice_Number(payment.getInvoiceNumber());
        }
        return dto;
    }

    @Override
    public List<Payment> findPaymentByBidding(Integer bidding_id) {
        return paymentRepository.findPaymentByBidding(bidding_id);
    }

    @Override
    public Boolean paySellerDeposit(Payment payment) {
        //TODO: payment with paypal
        //Update system
        payment.setPaySellerDate(LocalDateTime.now());
        paymentRepository.save(payment);
        return true;
    }

    @Override
    public Boolean payBidderFull(Payment payment) {
        //TODO: payment with paypal
        //Update system
        payment.setReturnDepositDate(LocalDateTime.now());
        payment.setReturnDeposit(payment.getDeposit() + payment.getRemainingAmount());
        paymentRepository.save(payment);
        return true;
    }
}
