package com.atguili.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:11:18
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

