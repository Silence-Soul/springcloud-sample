package com.atguili.gulimall.ware.dao;

import com.atguili.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:28:05
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
