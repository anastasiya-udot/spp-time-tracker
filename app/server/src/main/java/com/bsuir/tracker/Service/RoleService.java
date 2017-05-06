package com.bsuir.tracker.Service;

import com.bsuir.tracker.entity.RoleEntity;

import java.util.List;

/**
 * Created by Pavel on 26.04.2017.
 */
public interface RoleService {

    public void addRole(RoleEntity role);

    public RoleEntity getRole(int idRole);

    public List<RoleEntity> getAllRoles();

    public RoleEntity updateRole(RoleEntity role);

    public void deleteRole(int idRole);

}