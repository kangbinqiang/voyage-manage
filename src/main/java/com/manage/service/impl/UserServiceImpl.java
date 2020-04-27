package com.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.entity.UserDO;
import com.manage.mapper.UserMapper;
import com.manage.model.UserMO;
import com.manage.service.UserService;
import com.manage.utils.ShiroEncryption;
import com.manage.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDO fetchUser(UserMO userMO) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", userMO.getUsername());
        criteria.andEqualTo("password", userMO.getPassword());
        return userMapper.selectOneByExample(example);
    }

    @Override
    public void addUser(UserMO userMO) {
        UserDO userDO = new UserDO();
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        String password = ShiroEncryption.shiroEncryption(userMO.getPassword(), salt);
        Date date = new Date();
        BeanUtils.copyProperties(userMO, userDO);
        userDO.setId(StringUtil.generateUUID());
        userDO.setSalt(salt);
        userDO.setStatus(true);
        userDO.setPassword(password);
        userDO.setCreatedTime(date);
        userDO.setCreatedBy("admin");
        userDO.setUpdatedTime(date);
        userDO.setUpdatedBy("admin");
        userMapper.insertSelective(userDO);
    }

    @Override
    public UserDO fetUserByUserName(String username) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public PageInfo<UserDO> listUser(Integer pageNumber, Integer pageSize, String username) {
        PageHelper.startPage(pageNumber, pageSize);
        UserDO userDO = new UserDO();
        if (StringUtils.isNotBlank(username)) {
            userDO.setUsername(username);
        }
        List<UserDO> userDOList = userMapper.select(userDO);
        return new PageInfo<>(userDOList);
    }

    @Override
    public boolean updateUserStatus(String id, Boolean status) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        UserDO userDO = userMapper.selectOneByExample(example);
        if (userDO == null) {
            return false;
        }
        userDO.setStatus(status);
        userDO.setUpdatedTime(new Date());
        userDO.setUpdatedBy("admin");
        userMapper.updateByExampleSelective(userDO, example);
        return true;
    }

    @Override
    public UserMO fetUserById(String id) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        UserDO userDO = userMapper.selectOneByExample(example);
        UserMO userMO = new UserMO();
        BeanUtils.copyProperties(userDO, userMO);
        return userMO;
    }

    @Override
    public boolean updateUser(String id, String email, String mobile) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        UserDO userDO = userMapper.selectOneByExample(example);
        if (userDO == null) {
            return false;
        }
        userDO.setEmail(email);
        userDO.setMobile(mobile);
        userDO.setUpdatedTime(new Date());
        userDO.setUpdatedBy("admin");
        userMapper.updateByExampleSelective(userDO, example);
        return true;
    }

    @Override
    public void deleteUser(String id) {
        Example example = new Example(UserDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        userMapper.deleteByExample(example);
    }
}
