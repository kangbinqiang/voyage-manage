package com.manage.common;

import com.manage.entity.UserDO;
import com.manage.service.RedisService;
import com.manage.service.UserService;
import com.manage.utils.JWTUtil;
import com.manage.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public class MangeRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;


    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        log.info("用户认证开始------" + username);
        UserDO userDO = userService.fetUserByUserName(username);
        if (userDO == null) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        SecurityUtils.getSubject().getSession().setAttribute("user", userDO);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userDO.getUsername(), userDO.getPassword(),
                ByteSource.Util.bytes(userDO.getSalt()), getName());
        return authenticationInfo;
    }
}
