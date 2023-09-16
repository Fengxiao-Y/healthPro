package com.fx.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.fx.entity.PageResult;
import com.fx.entity.QueryPageBean;
import com.fx.pojo.Setmeal;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);

    PageResult pageQuery(QueryPageBean queryPageBean);

    List<Setmeal> findAll();

    Setmeal findById(int id);
}
