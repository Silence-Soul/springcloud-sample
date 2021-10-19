package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.SkuInfoEntity;

import java.util.Map;

/**
 * sku信息
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 */
public interface SkuInfoService extends IService<SkuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

