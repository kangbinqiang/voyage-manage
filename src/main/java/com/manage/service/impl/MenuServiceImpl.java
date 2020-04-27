package com.manage.service.impl;

import com.manage.entity.MenuDO;
import com.manage.mapper.MenuMapper;
import com.manage.model.MenuMO;
import com.manage.service.MenuService;
import com.manage.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {


    @Autowired
    private MenuMapper menuMapper;


    @Override
    public List<MenuMO> listMenu() {
        //目前只支持二级菜单
        List<MenuMO> result = new ArrayList<>();
        Example example = new Example(MenuDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("parentId");
        List<MenuDO> menuDOList = menuMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(menuDOList)) {
            return null;
        }
        result = convertDOToMO(menuDOList);
        result.forEach(menuMO -> {
            Example childExample = new Example(MenuDO.class);
            Example.Criteria childCriteria = childExample.createCriteria();
            childCriteria.andEqualTo("parentId",menuMO.getId());
            List<MenuDO> children = menuMapper.selectByExample(childExample);
            menuMO.setChildren(convertDOToMO(children));
        });
        return result;
    }

    @Override
    public void addMenu(MenuMO menuMO) {
        Date date = new Date();
        MenuDO menuDO = new MenuDO();
        menuDO.setId(StringUtil.generateUUID());
        menuDO.setMenuName(menuMO.getMenuName());
        menuDO.setPath(menuMO.getPath());
        menuDO.setIcon(menuMO.getIcon());
        menuDO.setParentId(menuMO.getParentId());
        menuDO.setCreatedTime(date);
        menuDO.setCreatedBy("admin");
        menuDO.setUpdatedTime(date);
        menuDO.setUpdatedBy("admin");
        menuMapper.insertSelective(menuDO);
    }

    List<MenuMO> convertDOToMO(List<MenuDO> list) {
        List<MenuMO> result = new ArrayList<>();
        result = list.stream().map(menuDO -> {
            MenuMO menuMO = new MenuMO();
            menuMO.setId(menuDO.getId());
            menuMO.setParentId(menuDO.getParentId());
            menuMO.setMenuName(menuDO.getMenuName());
            menuMO.setPath(menuDO.getPath());
            menuMO.setIcon(menuDO.getIcon());
            return menuMO;
        }).collect(Collectors.toList());
        return result;
    }
}
