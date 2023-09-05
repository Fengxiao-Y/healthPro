package com.fx.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.fx.constant.MessageConstant;
import com.fx.entity.PageResult;
import com.fx.entity.QueryPageBean;
import com.fx.entity.Result;
import com.fx.pojo.CheckItem;
import com.fx.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference//查找服务dubbo
    private CheckItemService checkItemService;

    //新增检查项
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try{
            checkItemService.add(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    //检查项分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);
        return pageResult;
    }
}
