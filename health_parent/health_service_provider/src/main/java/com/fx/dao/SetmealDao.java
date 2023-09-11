package com.fx.dao;

import com.fx.pojo.CheckGroup;
import com.fx.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.Map;

public interface SetmealDao {

    public void add(Setmeal setmeal);

    public void setSetmealAndCheckGroup(Map<String, Integer> map);

    Page<Setmeal> findByCondition(String queryString);
}
