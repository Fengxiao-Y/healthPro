package com.fx.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.fx.dao.CheckGroupDao;
import com.fx.entity.PageResult;
import com.fx.entity.QueryPageBean;
import com.fx.pojo.CheckGroup;
import com.fx.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    //新增检查组，同时需要设置多对多关联关系
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组，操作t_checkgroup表
        checkGroupDao.add(checkGroup);
        //设置检查组盒检查项目的关联关系，操作t_checkgroup_checkitem
        Integer checkGroupId = checkGroup.getId();
        this.setCheckGroupAndCheckItem(checkGroupId,checkitemIds);
    }

    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);//基于拦截器，本地线程绑定
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    //根据检查组ID查询检查项id
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑检查组信息，关联检查项
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查组基本信息
        checkGroupDao.edit(checkGroup);
        //清除当前检查组关联的检查项
        Integer checkGroupId = checkGroup.getId();
        checkGroupDao.deleteAssocication(checkGroupId);
        //重新设置关联关系
        this.setCheckGroupAndCheckItem(checkGroupId,checkitemIds);
    }

    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    //建立检查组盒检查项关联关系
     public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
         if(checkitemIds!=null && checkitemIds.length > 0){
             for (Integer checkitemId : checkitemIds) {
                 Map<String,Integer> map = new HashMap<String, Integer>();
                 map.put("checkgroupId",checkGroupId);
                 map.put("checkitemId",checkitemId);
                 checkGroupDao.setCheckGroupAndCheckItem(map);
             }
         }
     }
}
