package com.manage.service;

import com.manage.entity.RoleDO;
import com.manage.entity.RolePermissionDO;
import com.manage.model.PermissionVO;
import com.manage.model.RoleMO;

import java.util.List;

public interface RoleService {

    void addRole(RoleMO roleMO);

    List<RoleMO> listRole();

    RolePermissionDO fetchRolePermissionById(String roleId, String permissionId);

    List<PermissionVO> deleteRolePermissionById(String roleId, String permissionId);

    RoleDO fetchRoleById(String id);

    void updateRole(RoleMO roleMO);

    void deleteRole(String id);
}
