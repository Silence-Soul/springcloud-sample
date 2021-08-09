package com.atguili.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:11:18
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

