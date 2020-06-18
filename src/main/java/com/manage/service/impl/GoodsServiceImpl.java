package com.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.entity.CateDO;
import com.manage.entity.MenuDO;
import com.manage.mapper.CateMapper;
import com.manage.model.CateMO;
import com.manage.model.CateQueryMO;
import com.manage.model.CategoryMO;
import com.manage.service.GoodsService;
import com.manage.utils.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private CateMapper cateMapper;


    @Override
    public void ddCate(CateMO cateMO) {
        CateDO cateDO = new CateDO();
        BeanUtils.copyProperties(cateMO, cateDO);
        cateDO.setId(StringUtil.generateUUID());
        cateDO.setCateId(StringUtil.generateUUID());
        cateDO.setCreatedTime(new Date());
        cateDO.setCreatedBy("admin");
        cateDO.setUpdatedTime(new Date());
        cateDO.setUpdatedBy("admin");
        cateMapper.insertSelective(cateDO);
    }

    @Override
    public PageInfo<CategoryMO> listCate(CateQueryMO cateQueryMO) {
        PageHelper.startPage(cateQueryMO.getPageNumber(), cateQueryMO.getPageSize());
        CateDO cateDO = new CateDO();
        cateDO.setCateLevel(cateQueryMO.getCateLevel() != null ? cateQueryMO.getCateLevel() : 0);
        List<CateDO> all = cateMapper.select(cateDO);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        List<CategoryMO> result = all.stream().map(cate -> {
            CategoryMO categoryMO = new CategoryMO();
            categoryMO.setCateId(cate.getCateId());
            categoryMO.setCateName(cate.getCateName());
            categoryMO.setCateLevel(cate.getCateLevel());
            categoryMO.setCateStatus(cate.getCateStatus());
            categoryMO.setChildren(listSecondChild(cate.getCateId()));
            return categoryMO;
        }).collect(Collectors.toList());
        PageInfo<CateDO> pageInfo = new PageInfo<>(all);
        PageInfo<CategoryMO> pageInfoResult = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfoResult);
        pageInfoResult.setList(result);
        return pageInfoResult;
    }

    public List<CategoryMO> listSecondChild(String cateId) {
        Example example = new Example(CateDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", cateId);
        criteria.andEqualTo("cateLevel", 1);
        List<CateDO> all = cateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        return all.stream().map(cate -> {
            CategoryMO categoryMO = new CategoryMO();
            categoryMO.setCateId(cate.getCateId());
            categoryMO.setCateName(cate.getCateName());
            categoryMO.setCateLevel(cate.getCateLevel());
            categoryMO.setCateStatus(cate.getCateStatus());
            categoryMO.setChildren(listThirdChild(cate.getCateId()));
            return categoryMO;
        }).collect(Collectors.toList());
    }


    public List<CategoryMO> listThirdChild(String cateId) {
        Example example = new Example(CateDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", cateId);
        criteria.andEqualTo("cateLevel", 2);
        List<CateDO> all = cateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        return all.stream().map(cate -> {
            CategoryMO categoryMO = new CategoryMO();
            categoryMO.setCateId(cate.getCateId());
            categoryMO.setCateName(cate.getCateName());
            categoryMO.setCateLevel(cate.getCateLevel());
            categoryMO.setCateStatus(cate.getCateStatus());
            return categoryMO;
        }).collect(Collectors.toList());
    }
}
