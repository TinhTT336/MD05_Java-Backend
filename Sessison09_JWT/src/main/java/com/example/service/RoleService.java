package com.example.service;

import com.example.model.entity.Role;

public interface RoleService {
    Role findByRoleName(String roleName);
    void delete(Long id);
}
