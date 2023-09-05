package com.fx.dao;

import com.fx.entity.QueryPageBean;
import com.fx.pojo.CheckItem;
import com.github.pagehelper.Page;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);
}
