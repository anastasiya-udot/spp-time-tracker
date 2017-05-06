package com.bsuir.tracker.Service;

import com.bsuir.tracker.DAO.RoleDAO;
import com.bsuir.tracker.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    @Transactional
    public void addRole(RoleEntity role) {
        roleDAO.addRole(role);
    }

    @Override
    public RoleEntity getRole(int idRole) {
        return roleDAO.getRole(idRole);
    }

    @Transactional
    public List<RoleEntity> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    @Override
    public RoleEntity updateRole(RoleEntity role) {
        return roleDAO.updateRole(role);
    }

    @Transactional
    public void deleteRole(int idRole) {
        roleDAO.deleteRole(idRole);
    }

    public void setRoleDAO(RoleDAO roleDAO){
        this.roleDAO = roleDAO;
    }
}
