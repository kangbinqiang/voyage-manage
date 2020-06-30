package com.manage.service;

import com.github.pagehelper.PageInfo;
import com.manage.entity.GoodsDO;
import com.manage.model.*;

import java.util.List;

public interface GoodsService {

    void ddCate(CateMO cateMO);

    PageInfo<CategoryMO> listCate(CateQueryMO cateQueryMO);

    List<CategoryMO> listParentCate();

    void deleteCate(String cateId);

    List<ParamMO> listParams(String cateId, String type);

    void addParam(ParamMO paramMO);

    void addGoods(GoodsMO goodsMO);

    PageInfo<GoodsDO> pagingGoods(GoodsQueryMO queryMO);
}
