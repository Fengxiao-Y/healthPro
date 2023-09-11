package com.fx.service;

import com.fx.entity.PageResult;
import com.fx.entity.QueryPageBean;
import com.fx.pojo.Setmeal;

public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);
}
