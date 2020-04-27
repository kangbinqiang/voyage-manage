package com.manage.controller;


import com.manage.common.ResponseMO;
import com.manage.model.MenuMO;
import com.manage.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = "菜单管理")
@RequestMapping("/menu")
@Slf4j
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;


    @GetMapping("/list")
    @ApiOperation(value = "获取菜单")
    public ResponseMO<List<MenuMO>> listMenu() {
        List<MenuMO> result = menuService.listMenu();
        return success(result);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加菜单")
    public ResponseMO addMenu(@Valid @RequestBody MenuMO menuMO) {
        menuService.addMenu(menuMO);
        return success();
    }

}
