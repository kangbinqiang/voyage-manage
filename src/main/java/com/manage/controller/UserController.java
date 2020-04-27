package com.manage.controller;


import com.github.pagehelper.PageInfo;
import com.manage.common.ResponseMO;
import com.manage.entity.UserDO;
import com.manage.model.UserMO;
import com.manage.model.UserQueryMO;
import com.manage.service.UserService;
import com.manage.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@Api(tags = "用户管理")
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
//    @RequiresPermissions("role")
    public ResponseMO login(@Valid @RequestBody UserMO userMO, HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(userMO.getUsername(), userMO.getPassword());
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
//            String sessionCode = (String) request.getSession().getAttribute("code");
//            if (!sessionCode.toLowerCase().equals(userMO.getCode())) {
//                return error("验证码不正确");
//            }
            subject.login(token);
            if (subject.isAuthenticated()) {
                String loginToken = JWTUtil.createToken(userMO.getUsername());
                return success(loginToken);
            } else {
                return error("登录失败");
            }
        } catch (Exception e) {
            log.error("登录失败，用户=[{}]", userMO.getUsername());
        }
        return error("登录失败");
    }


    @PostMapping("/add")
    @ApiOperation(value = "添加用户")
    public ResponseMO addUser(@Valid @RequestBody UserMO userMO) {
        userService.addUser(userMO);
        return success();
    }

    @PostMapping("/list")
    @ApiOperation(value = "用户列表")
    public ResponseMO<PageInfo<UserDO>> listUser(@Valid @RequestBody UserQueryMO queryMO) {
        PageInfo<UserDO> result = userService.listUser(queryMO.getPageNumber(), queryMO.getPageSize(), queryMO.getUsername());
        return success(result);
    }

    @PutMapping("/update/{id}/status/{status}")
    @ApiOperation(value = "修改用户状态")
    public ResponseMO updateUserStatus(@PathVariable("id") String id, @PathVariable("status") Boolean status) {
        if (userService.updateUserStatus(id, status)) {
            return success();
        } else {
            return error("修改失败");
        }
    }

    @GetMapping("/fetch")
    @ApiOperation(value = "通过id查询用户")
    public ResponseMO<UserMO> fetUserById(@RequestParam("id") String id) {
        return success(userService.fetUserById(id));
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改用户信息")
    public ResponseMO updateUser(@Valid @RequestBody UserMO userMO) {
        if (userService.updateUser(userMO.getId(),userMO.getEmail(), userMO.getMobile())) {
            return success();
        } else {
            return error("修改失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除用户")
    public ResponseMO deleteUser(@PathVariable("id") String id) {
        if (userService.fetUserById(id) == null) {
            return error("用户不存在");
        }
        userService.deleteUser(id);
        return success();
    }

}
