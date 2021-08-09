package com.atguili.gulimall.order.dao;

import com.atguili.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:25:18
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
