package miu.edu.auction.service;


import miu.edu.auction.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role get(Integer id);
}
