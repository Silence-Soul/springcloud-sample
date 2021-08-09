package com.atguili.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.product.entity.SpuCommentEntity;

import java.util.Map;

/**
 * 商品评价
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 */
public interface SpuCommentService extends IService<SpuCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

