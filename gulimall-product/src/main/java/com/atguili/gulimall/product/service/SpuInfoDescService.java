package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

