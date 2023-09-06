package com.fx.dao;

import com.fx.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.Map;

public interface CheckGroupDao {

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Page<CheckGroup> findByCondition(String queryString);
}
