package com.atguili.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:11:18
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

