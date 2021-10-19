package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

