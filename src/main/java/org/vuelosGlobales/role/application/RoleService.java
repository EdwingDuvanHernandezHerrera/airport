package org.vuelosGlobales.role.application;

import org.vuelosGlobales.role.domain.Role;
import org.vuelosGlobales.role.infrastrustura.RoleRepository;

import java.util.List;
import java.util.Optional;

public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void createRole(Role role){
        this.roleRepository.save(role);
    }

    public void updateRole(Role role){
        this.roleRepository.update(role);
    }

    public Optional<Role> getRoleById(int id){
        return this.roleRepository.findById(id);
    }

    public List<Role> getAllRoles(){
        return this.roleRepository.findAll();
    }

    public void deleteRole(int id){
        this.roleRepository.delete(id);
    }
}
