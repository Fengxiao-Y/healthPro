package com.fx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fx.dao.CheckItemDao;
import com.fx.pojo.CheckItem;
import com.fx.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检查项服务
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    //注入DAO对象
    @Autowired
    private CheckItemDao checkItemDao;
    //新增
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
