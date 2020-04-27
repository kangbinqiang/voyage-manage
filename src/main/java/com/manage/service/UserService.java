package com.manage.service;

import com.github.pagehelper.PageInfo;
import com.manage.entity.UserDO;
import com.manage.model.UserMO;

public interface UserService {

    UserDO fetchUser(UserMO userMO);

    void addUser(UserMO userMO);

    UserDO fetUserByUserName(String username);

    PageInfo<UserDO> listUser(Integer pageNumber, Integer pageSize, String username);

    boolean updateUserStatus(String id, Boolean status);

    UserMO fetUserById(String id);

    boolean updateUser(String id, String email, String mobile);

    void deleteUser(String id);
}
