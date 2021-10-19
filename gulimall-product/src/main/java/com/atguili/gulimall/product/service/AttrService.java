package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.product.vo.AttrGroupRelationVo;
import com.atguili.gulimall.product.vo.AttrResponseVo;
import com.atguili.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.AttrEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:24
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBasePage(Map<String, Object> params, Long catelogId, String type);

    AttrResponseVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId);
}

