package com.manage.service;

import com.manage.entity.PermissionDO;
import com.manage.model.DispatcherMO;
import com.manage.model.PermissionMO;
import com.manage.model.PermissionVO;

import java.util.List;

public interface PermissionService {

    List<PermissionMO> listPermission();

    void addPermission(PermissionMO permissionMO);

    PermissionDO fetPermissionById(String permissionId);

    void deleteById(String permissionId);

    void deleteByPath(String path);

    List<PermissionVO> treePermission();

    void dispatcherPermission(DispatcherMO dispatcherMO);
}
