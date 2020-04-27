package com.manage.service.impl;


import com.manage.entity.PermissionDO;
import com.manage.entity.RoleDO;
import com.manage.entity.RolePermissionDO;
import com.manage.mapper.PermissionMapper;
import com.manage.mapper.RoleMapper;
import com.manage.mapper.RolePermissionMapper;
import com.manage.model.PermissionVO;
import com.manage.model.RoleMO;
import com.manage.service.PermissionService;
import com.manage.service.RoleService;
import com.manage.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public void addRole(RoleMO roleMO) {
        Date date = new Date();
        RoleDO roleDO = new RoleDO();
        roleDO.setId(StringUtil.generateUUID());
        roleDO.setName(roleMO.getName());
        roleDO.setDesc(roleMO.getDesc());
        roleDO.setCreatedTime(date);
        roleDO.setCreatedBy("admin");
        roleDO.setUpdatedTime(date);
        roleDO.setUpdatedBy("admin");
        roleMapper.insertSelective(roleDO);
    }

    @Override
    public List<RoleMO> listRole() {
        List<RoleMO> result = new ArrayList<>();
        List<RoleDO> all = roleMapper.selectAll();
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        for (RoleDO roleDO : all) {
            RoleMO roleMO = new RoleMO();
            roleMO.setId(roleDO.getId());
            roleMO.setName(roleDO.getName());
            roleMO.setDesc(roleDO.getDesc());
            //查找角色-权限
            Example permissionExample = new Example(RolePermissionDO.class);
            Example.Criteria permissionCriteria = permissionExample.createCriteria();
            permissionCriteria.andEqualTo("roleId", roleDO.getId());
            List<RolePermissionDO> permissions = rolePermissionMapper.selectByExample(permissionExample);
            if (!CollectionUtils.isEmpty(permissions)) {
                //查找权限
                List<PermissionVO> permissionVOS = listPermissionsByRelationship(permissions);
                roleMO.setPermissions(permissionVOS);
            }
            result.add(roleMO);
        }
        return result;
    }

    @Override
    public RolePermissionDO fetchRolePermissionById(String roleId, String permissionId) {
        Example example = new Example(RolePermissionDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        criteria.andEqualTo("permissionId", permissionId);
        return rolePermissionMapper.selectOneByExample(example);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PermissionVO> deleteRolePermissionById(String roleId, String permissionId) {
        Example example = new Example(RolePermissionDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", roleId);
        criteria.andEqualTo("permissionId", permissionId);
        //二级权限删除一个
        PermissionDO permissionDO = permissionService.fetPermissionById(permissionId);
        rolePermissionMapper.deleteByExample(example);
        if ("2".equals(permissionDO)) {
            permissionService.deleteById(permissionId);
        }
        //一级权限删除所有
        if ("1".equals(permissionDO.getLevel())) {
            permissionService.deleteByPath(permissionDO.getPath());
        }
        //获取新的权限
        Example permissionExample = new Example(RolePermissionDO.class);
        Example.Criteria permissionCriteria = permissionExample.createCriteria();
        permissionCriteria.andEqualTo("roleId", roleId);
        List<RolePermissionDO> permissions = rolePermissionMapper.selectByExample(permissionExample);
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        List<PermissionVO> permissionVOS = listPermissionsByRelationship(permissions);
        return permissionVOS;
    }

    @Override
    public RoleDO fetchRoleById(String id) {
        Example example = new Example(RoleDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        return roleMapper.selectOneByExample(example);
    }

    @Override
    public void updateRole(RoleMO roleMO) {
        RoleDO roleDO = fetchRoleById(roleMO.getId());
        roleDO.setName(roleMO.getName());
        roleDO.setDesc(roleMO.getDesc());
        Example example = new Example(RoleDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", roleMO.getId());
        roleMapper.updateByExample(roleDO, example);
    }

    @Override
    public void deleteRole(String id) {
        Example example = new Example(RoleDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", id);
        roleMapper.deleteByExample(example);
        Example relExample = new Example(RolePermissionDO.class);
        Example.Criteria relCriteria = relExample.createCriteria();
        relCriteria.andEqualTo("roleId", id);
        rolePermissionMapper.deleteByExample(relExample);
    }

    /**
     * 查找权限树
     *
     * @param permissions
     * @return
     */
    private List<PermissionVO> listPermissionsByRelationship(List<RolePermissionDO> permissions) {
        List<PermissionVO> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(permissions)) {
            return null;
        }
        //查找所有的权限
        List<PermissionDO> permissionDOList = new ArrayList<>();
        for (RolePermissionDO permission : permissions) {
            Example example = new Example(PermissionDO.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("id", permission.getPermissionId());
            PermissionDO permissionDO = permissionMapper.selectOneByExample(example);
            if (permissionDO == null) {
                continue;
            }
            permissionDOList.add(permissionDO);
        }
        if (CollectionUtils.isEmpty(permissionDOList)) {
            return null;
        }
        for (PermissionDO permissionDO : permissionDOList) {
            PermissionVO permissionVO1 = new PermissionVO();
            //1级权限
            if ("1".equalsIgnoreCase(permissionDO.getLevel())) {
                permissionVO1.setId(permissionDO.getId());
                permissionVO1.setName(permissionDO.getName());
                permissionVO1.setPath(permissionDO.getPath());
                permissionVO1.setLevel(permissionDO.getLevel());
                //二级权限，且路径相同
                List<PermissionVO> temp = new ArrayList<>();
                for (PermissionDO permission : permissionDOList) {
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


}
