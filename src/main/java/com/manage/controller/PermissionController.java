package com.manage.controller;


import com.manage.common.ResponseMO;
import com.manage.model.DispatcherMO;
import com.manage.model.PermissionMO;
import com.manage.model.PermissionVO;
import com.manage.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "权限管理")
@RequestMapping("/permission")
@Slf4j
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    @ApiOperation(value = "权限列表展示")
    public ResponseMO<List<PermissionMO>> listPermission() {
        return success(permissionService.listPermission());
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加权限")
    public ResponseMO addPermission(@Valid @RequestBody PermissionMO permissionMO) {
        permissionService.addPermission(permissionMO);
        return success();
    }

    @GetMapping("/tree")
    @ApiOperation(value = "获取权限树")
    public ResponseMO<List<PermissionVO>> treePermission() {
        return success(permissionService.treePermission());
    }

    @PostMapping("/select")
    @ApiOperation(value = "分配权限")
    public ResponseMO dispatcherPermission(@Valid @RequestBody DispatcherMO dispatcherMO) {
        permissionService.dispatcherPermission(dispatcherMO);
        return success();
    }

}
