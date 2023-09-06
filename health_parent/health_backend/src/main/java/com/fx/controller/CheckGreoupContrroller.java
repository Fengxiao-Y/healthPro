package com.fx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fx.constant.MessageConstant;
import com.fx.entity.Result;
import com.fx.pojo.CheckGroup;
import com.fx.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGreoupContrroller {
    @Reference
    private CheckGroupService checkGroupService;

    //新增检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
}
