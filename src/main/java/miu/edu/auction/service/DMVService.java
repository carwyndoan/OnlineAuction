package miu.edu.auction.service;

import miu.edu.auction.domain.DMV;

public interface DMVService {
    DMV getDMV(String name, String license);

}
