package com.atguili.gulimall.product.service.impl;

import com.atguili.common.constant.ProductConstant;
import com.atguili.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.atguili.gulimall.product.dao.AttrGroupDao;
import com.atguili.gulimall.product.dao.CategoryDao;
import com.atguili.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguili.gulimall.product.entity.AttrGroupEntity;
import com.atguili.gulimall.product.entity.CategoryEntity;
import com.atguili.gulimall.product.service.CategoryService;
import com.atguili.gulimall.product.vo.AttrGroupRelationVo;
import com.atguili.gulimall.product.vo.AttrResponseVo;
import com.atguili.gulimall.product.vo.AttrVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.Query;

import com.atguili.gulimall.product.dao.AttrDao;
import com.atguili.gulimall.product.entity.AttrEntity;
import com.atguili.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao attrAttrgroupRelationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryService categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        //1 保存基本数据
        this.save(attrEntity);

        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() && attr.getAttrGroupId() != null){
            //2 保存关联关系
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attrEntity.getAttrId());
            attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
        }
    }

    @Override
    public PageUtils queryBasePage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<AttrEntity> attrEntityQueryWrapper =
                new QueryWrapper<AttrEntity>()
                        .eq("attr_type", "base".equalsIgnoreCase(type)
                                ?ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()
                                :ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if(catelogId != 0){
            attrEntityQueryWrapper.eq("catelog_id", catelogId);
        }
        // 关键字
        String key = (String)params.get("key");
        if(StringUtils.hasText(key)){
            attrEntityQueryWrapper.and((wrapper)->{
                wrapper.eq("attr_id", key).or().like("attr_name", key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                attrEntityQueryWrapper
        );

        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();

        List<AttrResponseVo> reList = records.stream().map((attrEntity) -> {
            AttrResponseVo attrResponseVo = new AttrResponseVo();
            BeanUtils.copyProperties(attrEntity, attrResponseVo);

            // 设置分类和分组的名字
            AttrAttrgroupRelationEntity attrGroupRelation = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));

            if("base".equalsIgnoreCase(type)){
                if (attrGroupRelation != null) {
                    Long attrGroupId = attrGroupRelation.getAttrGroupId();
                    AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }

            CategoryEntity categoryEntity = categoryDao.selectById(attrEntity.getCatelogId());

            if (categoryEntity != null) {
                attrResponseVo.setCatelogName(categoryEntity.getName());
            }

            return attrResponseVo;
        }).collect(Collectors.toList());

        pageUtils.setList(reList);
        return pageUtils;
    }

    @Override
    public AttrResponseVo getAttrInfo(Long attrId) {
        AttrResponseVo attrResponseVo = new AttrResponseVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, attrResponseVo);

        if(attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            // 设置分组信息
            AttrAttrgroupRelationEntity attrGroupRelation = attrAttrgroupRelationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attrEntity.getAttrId()));
            if(attrGroupRelation != null && attrGroupRelation.getAttrGroupId() != null){
                attrResponseVo.setAttrGroupId(attrGroupRelation.getAttrGroupId());
                AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupRelation.getAttrGroupId());
                if(attrGroupEntity != null){
                    attrResponseVo.setGroupName(attrGroupEntity.getAttrGroupName());
                }
            }
        }

        //2 设置分类信息
        Long catelogId = attrEntity.getCatelogId();
        Long[] catelogPath = categoryService.findCatelogPath(catelogId);
        attrResponseVo.setCatelogPath(catelogPath);

        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        if(categoryEntity != null){
            attrResponseVo.setCatelogName(categoryEntity.getName());
        }

        return attrResponseVo;
    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        if(attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()){
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            attrAttrgroupRelationEntity.setAttrGroupId(attr.getAttrGroupId());
            attrAttrgroupRelationEntity.setAttrId(attr.getAttrId());

            Integer count = attrAttrgroupRelationDao.selectCount(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));

            if(count > 0){
                // 修改分组关联
                attrAttrgroupRelationDao.update(attrAttrgroupRelationEntity, new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            } else {
                attrAttrgroupRelationDao.insert(attrAttrgroupRelationEntity);
            }
        }
    }

    /**
     * 根据分组 id 查找关联的基本属性
     * @param attrgroupId
     * @return
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {
        List<AttrEntity> attrEntities = new ArrayList<>();
        List<AttrAttrgroupRelationEntity> attrRelations = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", attrgroupId));

        if(attrRelations.size() > 0){
            List<Long> attrids = attrRelations.stream().map((attr) -> attr.getAttrId()).collect(Collectors.toList());

            attrEntities = this.listByIds(attrids);
        }
        return attrEntities;
    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos) {
        //attrAttrgroupRelationDao.delete(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_id",).eq("attr_group_id", ))

        List<AttrAttrgroupRelationEntity> attrAttrgroupRelationEntities = Arrays.asList(attrGroupRelationVos).stream().map((item) -> {
            AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
            BeanUtils.copyProperties(item, attrAttrgroupRelationEntity);
            return attrAttrgroupRelationEntity;
        }).collect(Collectors.toList());


        attrAttrgroupRelationDao.deleteBatchByRelation(attrAttrgroupRelationEntities);
    }

    // 获取当前分组没有关联的所有属性
    @Override
    public PageUtils getNoRelationAttr(Map<String, Object> params, Long attrgroupId) {
        // 1当前分组只能关联自己所属分类的所有属性
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrgroupId);
        Long catelogId = attrGroupEntity.getCatelogId();
        // 2当前分组只能关联别的分组没有引用胡属性
        // 2.1当前分类下在所有分组
        List<AttrGroupEntity> otherGroups= attrGroupDao.selectList(new QueryWrapper<AttrGroupEntity>().eq("catelog_id", catelogId));
        // 2.2这些分组关联的属性
        List<Long> attrGroupIds = otherGroups.stream().map((item) -> item.getAttrGroupId()).collect(Collectors.toList());

        List<AttrAttrgroupRelationEntity> relationEntity = attrAttrgroupRelationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().in("attr_group_id", attrGroupIds));
        List<Long> attrids = relationEntity.stream().map(item -> item.getAttrId()).collect(Collectors.toList());
        // 2.3从当前分类的属性中移除这些属性
        QueryWrapper<AttrEntity> attrEntityQueryWrapper = new QueryWrapper<AttrEntity>().eq("catelog_id", catelogId).eq("attr_type", ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode());
        if(null != attrids && attrids.size() > 0){
            attrEntityQueryWrapper.notIn("attr_id", attrids);
        }

        // 模糊查询条件
        String key = (String) params.get("key");
        if(StringUtils.hasText(key)){
             attrEntityQueryWrapper.and(w -> w.eq("attr_id", key).or().like("attr_name", key));
        }
        IPage<AttrEntity> page = this.page(new Query<AttrEntity>().getPage(params), attrEntityQueryWrapper);
        PageUtils pageUtils = new PageUtils(page);
        return pageUtils;
    }

}