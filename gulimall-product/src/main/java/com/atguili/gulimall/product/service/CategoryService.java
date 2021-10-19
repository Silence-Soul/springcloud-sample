package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:24
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenusByIds(List<Long> ids);

    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);
}

