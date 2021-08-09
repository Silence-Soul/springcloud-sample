package com.atguili.gulimall.product.dao;

import com.atguili.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:24
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
