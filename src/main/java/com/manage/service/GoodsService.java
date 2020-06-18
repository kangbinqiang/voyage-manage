package com.manage.service;

import com.github.pagehelper.PageInfo;
import com.manage.model.CateMO;
import com.manage.model.CateQueryMO;
import com.manage.model.CategoryMO;

public interface GoodsService {

    void ddCate(CateMO cateMO);

    PageInfo<CategoryMO> listCate(CateQueryMO cateQueryMO);
}
