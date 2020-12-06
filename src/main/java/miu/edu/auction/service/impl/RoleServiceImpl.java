package miu.edu.auction.service.impl;


import miu.edu.auction.domain.Role;
import miu.edu.auction.repository.RoleRepository;
import miu.edu.auction.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role get(Integer id) {
        return roleRepository.findById(id).get();
    }

}
