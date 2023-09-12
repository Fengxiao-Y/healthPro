package com.fx.dao;

import com.fx.pojo.OrderSetting;

import java.util.Date;

public interface OrderSettingDao {
    public void add(OrderSetting orderSetting);
    public long editNumberByOrderDate(OrderSetting orderSetting);
    public long findCountByOrderDate(Date orderDate);
}
