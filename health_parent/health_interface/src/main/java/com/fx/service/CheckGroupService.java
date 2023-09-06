package com.fx.service;

import com.fx.entity.PageResult;
import com.fx.entity.QueryPageBean;
import com.fx.pojo.CheckGroup;

public interface CheckGroupService {

    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    public PageResult pageQuery(QueryPageBean queryPageBean);
}
