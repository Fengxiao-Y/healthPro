package com.fx.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fx.constant.MessageConstant;
import com.fx.entity.Result;
import com.fx.pojo.OrderSetting;
import com.fx.service.OrderSettingService;
import com.fx.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 预约设置
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    //文件上传，实现批量导入
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile")MultipartFile excElFile){
        try {
            //使用poi解析表格数据
            List<String[]> list = POIUtils.readExcel(excElFile);
            List<OrderSetting> data = new ArrayList<OrderSetting>();
            for (String[] strings : list) {
                String orderDate = strings[0];
                String number = strings[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderDate),Integer.parseInt(number));
                data.add(orderSetting);
            }
            //通过dubbo远程服务实现批量导入到数据库
            orderSettingService.add(data);
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            //文件解析失败
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    //查询当前月设置的预约数据
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        //date格式yyyy-MM
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        } catch (Exception e) {
            //文件解析失败
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }
}
