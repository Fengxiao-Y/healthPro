package com.fx.service;

import com.fx.entity.PageResult;
import com.fx.entity.QueryPageBean;
import com.fx.pojo.CheckItem;

/**
 * 服务接口
 */
public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);
}