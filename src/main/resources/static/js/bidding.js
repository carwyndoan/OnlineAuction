// A $( document ).ready() block.
$(document).ready(function() {
    $("#btnBid").click(function() {
        let data = {
            "bidding_id": $("#bidding_id").val(),
            "user_id": $("#user_id").val(),
            "bid_amount": $("#bid_value").val()
        }
        placeBid(JSON.stringify(data));
    });
});

function placeBid(data) {
    const url = "http://localhost:8080/bid/placebid";
    $.ajax({
        url: url,
        type: "POST",
        data: data,
        contentType: "application/json",
        dataType: "json",
        success: updateBid,
        error: showErrors
    });
}

function updateBid(result) {
    console.log(result);
    $("#total_bidder").empty().append(result.total_bidder + " bids");
    $("#current_bid").empty().append(result.current_bid);
    $("#your_bid").empty().append(result.bid_amount);
    $("#bid_value").empty();
}

function showErrors(XMLHttpRequest, textStatus, errorThrown) {
    // make_visible('errors');
    // make_hidden('formInput');
    // $("#errors").empty();
    // let errorObject = XMLHttpRequest.responseJSON;
    // console.log(errorObject);
    // if (errorObject.errorType === "ValidationError") {
    //     let errorMsg = '<h3> Error(s)!! </h3>';
    //     errorMsg += "<p>";
    //     var errorList = errorObject.errors;
    //     $.each(errorList, function(i, error) {
    //         errorMsg = errorMsg + error.message + '<br>';
    //     });
    //     errorMsg += '</p>';
    //     $('#errors').append(errorMsg);
    //     $('#errors').show();
    // } else {
    //     alert(errorObject.errors(0)); // "non" Validation
    // }
}