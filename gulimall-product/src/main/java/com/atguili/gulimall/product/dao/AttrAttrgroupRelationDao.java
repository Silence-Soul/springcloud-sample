package com.atguili.gulimall.product.dao;

import com.atguili.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:24
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    void deleteBatchByRelation(@Param("attrAttrgroupRelationEntities") List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities);
}
