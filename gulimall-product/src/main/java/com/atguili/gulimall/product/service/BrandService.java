package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);
}

