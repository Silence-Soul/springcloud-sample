package com.atguili.gulimall.product.dao;

import com.atguili.gulimall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:23
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
