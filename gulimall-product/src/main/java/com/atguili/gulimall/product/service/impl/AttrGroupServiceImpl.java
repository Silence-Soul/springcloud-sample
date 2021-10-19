package com.atguili.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.Query;

import com.atguili.gulimall.product.dao.AttrGroupDao;
import com.atguili.gulimall.product.entity.AttrGroupEntity;
import com.atguili.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catalogId) {
        // 按照三级分类查
        String key = (String)params.get("key"); // 前端传来的 关键字 key
        QueryWrapper<AttrGroupEntity> attrGroupEntityQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(key)) {
            attrGroupEntityQueryWrapper.and((condition)->{
                condition.eq("attr_group_id", key).or().like("attr_group_name", key);
            });
        }

        if (catalogId == 0) {
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    attrGroupEntityQueryWrapper
            );
            return new PageUtils(page);
        } else {
            attrGroupEntityQueryWrapper.eq("catelog_id", catalogId);
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    attrGroupEntityQueryWrapper);

            return new PageUtils(page);
        }
    }
}