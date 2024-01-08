package com.example.service;

import com.example.model.entity.Role;
import com.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
