package com.manage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.common.CommonConstants;
import com.manage.entity.CateDO;
import com.manage.entity.GoodsDO;
import com.manage.entity.ParamDO;
import com.manage.mapper.CateMapper;
import com.manage.mapper.GoodsMapper;
import com.manage.mapper.ParamMapper;
import com.manage.model.*;
import com.manage.service.GoodsService;
import com.manage.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private CateMapper cateMapper;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    private GoodsMapper goodsMapper;


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
        cateDO.setCateName(StringUtils.isNotBlank(cateQueryMO.getCateName()) ? cateQueryMO.getCateName() : null);
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
            categoryMO.setParentId(cate.getParentId());
            categoryMO.setChildren(listSecondChild(cate.getCateId()));
            return categoryMO;
        }).collect(Collectors.toList());
        PageInfo<CateDO> pageInfo = new PageInfo<>(all);
        PageInfo<CategoryMO> pageInfoResult = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageInfoResult);
        pageInfoResult.setList(result);
        return pageInfoResult;
    }

    @Override
    public List<CategoryMO> listParentCate() {
        Example example = new Example(CateDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cateLevel", 0);
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
            categoryMO.setParentId(cate.getParentId());
            categoryMO.setChildren(listParentSecondChild(cate.getCateId()));
            return categoryMO;
        }).collect(Collectors.toList());
    }

    @Override
    public void deleteCate(String cateId) {
        Example example = new Example(CateDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cateId", cateId);
        CateDO cateDO = cateMapper.selectOneByExample(example);
        if (cateDO == null) {
            return;
        }
        if (CommonConstants.ONE.equals(cateDO.getCateLevel())) {
            //删除第三级
            deleteThird(cateId);
        }
        if (CommonConstants.ZERO.equals(cateDO.getCateLevel())) {
            //删除第二级和第三级
            deleteSecond(cateId);
        }
        cateMapper.deleteByExample(example);
    }

    @Override
    public List<ParamMO> listParams(String cateId, String type) {
        Example example = new Example(ParamDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("cateId", cateId);
        criteria.andEqualTo("type", type);
        List<ParamDO> all = paramMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(all)) {
            return null;
        }
        return all.stream().map(paramDO -> {
            ParamMO paramMO = new ParamMO();
            BeanUtils.copyProperties(paramDO, paramMO);
            return paramMO;
        }).collect(Collectors.toList());
    }

    @Override
    public void addParam(ParamMO paramMO) {
        ParamDO paramDO = new ParamDO();
        BeanUtils.copyProperties(paramMO, paramDO);
        paramDO.setId(StringUtil.generateUUID());
        paramDO.setCreatedTime(new Date());
        paramDO.setCreatedBy("admin");
        paramDO.setUpdatedTime(new Date());
        paramDO.setUpdatedBy("admin");
        paramMapper.insertSelective(paramDO);
    }

    @Override
    public void addGoods(GoodsMO goodsMO) {
        GoodsDO goodsDO = new GoodsDO();
        BeanUtils.copyProperties(goodsMO, goodsDO);
        goodsDO.setGoodsId(StringUtil.generateUUID());
        goodsDO.setId(StringUtil.generateUUID());
        goodsDO.setCreatedTime(new Date());
        goodsDO.setCreatedBy("admin");
        goodsDO.setUpdatedTime(new Date());
        goodsDO.setUpdatedBy("admin");
        goodsMapper.insertSelective(goodsDO);
    }

    @Override
    public PageInfo<GoodsDO> pagingGoods(GoodsQueryMO queryMO) {
        PageHelper.startPage(queryMO.getPageNumber(), queryMO.getPageSize());
        GoodsDO goodsDO = new GoodsDO();
        if (StringUtils.isNotBlank(queryMO.getGoodsName())) {
            goodsDO.setGoodsName(queryMO.getGoodsName());
        }
        List<GoodsDO> all = goodsMapper.select(goodsDO);
        return new PageInfo<>(all);
    }

    public void deleteSecond(String cateId) {
        Example example = new Example(CateDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", cateId);
        criteria.andEqualTo("cateLevel", 1);
        List<CateDO> all = cateMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(all)) {
            return;
        }
        all.forEach(cate -> {
            deleteThird(cate.getCateId());
        });
        cateMapper.deleteByExample(example);
    }

    public void deleteThird(String cateId) {
        Example example = new Example(CateDO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", cateId);
        criteria.andEqualTo("cateLevel", 2);
        cateMapper.deleteByExample(example);
    }

    public List<CategoryMO> listParentSecondChild(String cateId) {
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
            categoryMO.setParentId(cate.getParentId());
            return categoryMO;
        }).collect(Collectors.toList());
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
            categoryMO.setParentId(cate.getParentId());
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
            categoryMO.setParentId(cate.getParentId());
            return categoryMO;
        }).collect(Collectors.toList());
    }
}
