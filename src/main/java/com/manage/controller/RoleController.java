package com.manage.controller;


import com.manage.common.ResponseMO;
import com.manage.entity.RoleDO;
import com.manage.entity.RolePermissionDO;
import com.manage.model.PermissionVO;
import com.manage.model.RoleMO;
import com.manage.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "角色管理")
@Slf4j
public class RoleController extends BaseController {


    @Autowired
    private RoleService roleService;


    @PostMapping("/add")
    @ApiOperation(value = "添加角色")
    public ResponseMO addRole(@Valid @RequestBody RoleMO roleMO) {
        roleService.addRole(roleMO);
        return success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "展示角色")
    public ResponseMO<List<RoleMO>> listRole() {
        List<RoleMO> result = roleService.listRole();
        return success(result);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改角色")
    public ResponseMO updateRole(@Valid @RequestBody RoleMO roleMO) {
        RoleDO roleDO = roleService.fetchRoleById(roleMO.getId());
        if (roleDO == null) {
            return error("该记录不存在");
        }
        roleService.updateRole(roleMO);
        return success();
    }


    @GetMapping("/fetch")
    @ApiOperation(value = "通过id获取角色")
    public ResponseMO<RoleDO> fetRoleById(@RequestParam("id") String id) {
        return success(roleService.fetchRoleById(id));
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色")
    public ResponseMO deleteRole(@PathVariable("id") String id) {
        RoleDO roleDO = roleService.fetchRoleById(id);
        if (roleDO == null) {
            return error("该记录不存在");
        }
        roleService.deleteRole(id);
        return success();
    }

    @DeleteMapping("/{roleId}/permission/{permissionId}")
    @ApiOperation(value = "删除角色对应的权限")
    public ResponseMO<List<PermissionVO>> deletePermissionById(@PathVariable("roleId") String roleId,
                                                         @PathVariable("permissionId") String permissionId) {
        RolePermissionDO permissionDO = roleService.fetchRolePermissionById(roleId,permissionId);
        if (permissionDO == null ) {
            return error("删除失败，记录不存在");
        }
        return success(roleService.deleteRolePermissionById(roleId,permissionId));
    }




}
