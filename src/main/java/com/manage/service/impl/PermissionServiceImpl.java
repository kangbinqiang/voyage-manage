package com.manage.service.impl;

import com.manage.entity.MenuDO;
import com.manage.entity.PermissionDO;
import com.manage.entity.RolePermissionDO;
import com.manage.mapper.PermissionMapper;
import com.manage.mapper.RolePermissionMapper;
import com.manage.model.DispatcherMO;
import com.manage.model.PermissionMO;
import com.manage.model.PermissionVO;
import com.manage.service.PermissionService;
import com.manage.service.RoleService;
import com.manage.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PermissionServiceImpl implements PermissionService {


    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<PermissionMO> listPermission() {
        List<PermissionDO> all = permissionMapper.selectAll();
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        return all.stream().map(permissionDO -> {
            PermissionMO permissionMO = new PermissionMO();
            permissionMO.setId(permissionDO.getId());
            permissionMO.setName(permissionDO.getName());
            permissionMO.setPath(permissionDO.getPath());
            permissionMO.setLevel(permissionDO.getLevel());
            return permissionMO;
        }).collect(Collectors.toList());
    }

    @Override
    public void addPermission(PermissionMO permissionMO) {
        Date date = new Date();
        PermissionDO permissionDO = new PermissionDO();
        permissionDO.setId(StringUtil.generateUUID());
        permissionDO.setName(permissionMO.getName());
        permissionDO.setPath(permissionMO.getPath());
        permissionDO.setLevel(permissionMO.getLevel());
        permissionDO.setCreatedTime(date);
        permissionDO.setCreatedBy("admin");
        permissionDO.setUpdatedTime(date);
        permissionDO.setUpdatedBy("admin");
        permissionMapper.insertSelective(permissionDO);
    }

    @Override
    public PermissionDO fetPermissionById(String permissionId) {
        Example example = new Example(PermissionDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", permissionId);
        return permissionMapper.selectOneByExample(example);
    }

    @Override
    public void deleteById(String permissionId) {
        Example example = new Example(PermissionDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", permissionId);
        permissionMapper.deleteByExample(example);
    }

    @Override
    public void deleteByPath(String path) {
        Example example = new Example(PermissionDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("path", path);
        permissionMapper.deleteByExample(example);
    }

    @Override
    public List<PermissionVO> treePermission() {

        List<PermissionVO> result = new ArrayList<>();
        List<PermissionDO> permissions = permissionMapper.selectAll();
        for (PermissionDO permissionDO : permissions) {
            PermissionVO permissionVO1 = new PermissionVO();
            //1级权限
            if ("1".equalsIgnoreCase(permissionDO.getLevel())) {
                permissionVO1.setId(permissionDO.getId());
                permissionVO1.setName(permissionDO.getName());
                permissionVO1.setPath(permissionDO.getPath());
                permissionVO1.setLevel(permissionDO.getLevel());
                //二级权限，且路径相同
                List<PermissionVO> temp = new ArrayList<>();
                for (PermissionDO permission : permissions) {
                    PermissionVO permissionVO2 = new PermissionVO();
                    if ("2".equalsIgnoreCase(permission.getLevel())
                            && permissionDO.getPath().equalsIgnoreCase(permission.getPath())) {
                        permissionVO2.setId(permission.getId());
                        permissionVO2.setName(permission.getName());
                        permissionVO2.setPath(permission.getPath());
                        permissionVO2.setLevel(permission.getLevel());
                        temp.add(permissionVO2);
                    }
                }
                permissionVO1.setPermissions(temp);
                result.add(permissionVO1);
            }
        }
        return result;
    }

    @Override
    public void dispatcherPermission(DispatcherMO dispatcherMO) {
        if (StringUtils.isEmpty(dispatcherMO.getRoleId()) || CollectionUtils.isEmpty(dispatcherMO.getIds())) {
            return;
        }
        for (String permissionId : dispatcherMO.getIds()) {
            Example example = new Example(RolePermissionDO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("roleId",dispatcherMO.getRoleId());
            criteria.andEqualTo("permissionId",permissionId);
            RolePermissionDO rolePermissionDO = rolePermissionMapper.selectOneByExample(example);
            if (rolePermissionDO != null) {
                continue;
            }
            rolePermissionDO = new RolePermissionDO();
            rolePermissionDO.setId(StringUtil.generateUUID());
            rolePermissionDO.setRoleId(dispatcherMO.getRoleId());
            rolePermissionDO.setPermissionId(permissionId);
            rolePermissionDO.setCreatedTime(new Date());
            rolePermissionDO.setCreatedBy("admin");
            rolePermissionDO.setUpdatedTime(new Date());
            rolePermissionDO.setUpdatedBy("admin");
            rolePermissionMapper.insertSelective(rolePermissionDO);
        }
    }
}
