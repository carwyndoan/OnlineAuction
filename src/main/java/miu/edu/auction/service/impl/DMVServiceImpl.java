package miu.edu.auction.service.impl;

import miu.edu.auction.domain.DMV;
import miu.edu.auction.repository.DMVRepository;
import miu.edu.auction.service.DMVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DMVServiceImpl implements DMVService {

    @Autowired
    DMVRepository dmvRepository;
    @Override
    public DMV getDMV(String name, String license) {


        return dmvRepository.findByNameAndLicense(name,license);
    }
}
