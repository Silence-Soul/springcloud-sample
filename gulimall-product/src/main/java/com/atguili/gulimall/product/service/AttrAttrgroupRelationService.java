package com.atguili.gulimall.product.service;

import com.atguili.common.utils.PageUtils;
import com.atguili.gulimall.product.vo.AttrGroupRelationVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.atguili.gulimall.product.entity.AttrAttrgroupRelationEntity;

import java.util.List;
import java.util.Map;

/**
 * 属性&属性分组关联
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 11:03:24
 */
public interface AttrAttrgroupRelationService extends IService<AttrAttrgroupRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveBatch(List<AttrGroupRelationVo> vos);
}

