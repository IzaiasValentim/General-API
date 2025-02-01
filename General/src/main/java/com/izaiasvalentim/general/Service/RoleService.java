package com.izaiasvalentim.general.Service;

import com.izaiasvalentim.general.Common.CustomExceptions.ResourceNotFoundException;
import com.izaiasvalentim.general.Domain.Role;
import com.izaiasvalentim.general.Repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role with id " + id + " not found"));
    }
}
