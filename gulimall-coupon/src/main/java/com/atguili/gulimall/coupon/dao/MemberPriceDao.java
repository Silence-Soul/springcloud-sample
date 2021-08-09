package com.atguili.gulimall.coupon.dao;

import com.atguili.gulimall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 15:11:18
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}
