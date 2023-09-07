package com.fx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fx.dao.SetmealDao;
import com.fx.pojo.Setmeal;
import com.fx.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 体检套餐服务
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService{

    @Autowired
    private SetmealDao setmealDao;

    //新增套餐信息，同时设置关联检查组
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        Integer setmealId = setmeal.getId();
        setSetmealAndCheckgroup(setmealId,checkgroupIds);
    }

    //设置套餐和检查组多对多关系
    public void setSetmealAndCheckgroup(Integer setmealId,Integer[] checkgroupIds){
        if(checkgroupIds!=null && checkgroupIds.length>0){
            for (Integer checkgroupId : checkgroupIds) {
                Map<String,Integer> map = new HashMap<String, Integer>();
                map.put("setmealid",setmealId);
                map.put("checkgroupId",checkgroupId);
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }
}
