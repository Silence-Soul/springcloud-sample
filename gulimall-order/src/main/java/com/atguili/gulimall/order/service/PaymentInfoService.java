package com.atguili.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.order.entity.PaymentInfoEntity;

import java.util.Map;

/**
 * 支付信息表
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:25:18
 */
public interface PaymentInfoService extends IService<PaymentInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

