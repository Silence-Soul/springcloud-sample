package com.atguili.gulimall.product.service.impl;

import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.Query;
import com.atguili.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguili.gulimall.product.dao.CategoryDao;
import com.atguili.gulimall.product.entity.CategoryEntity;
import com.atguili.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {

        // 查出所有分类
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        // 组装父子树形结构

        // 1、找到所有的一级分类
        List<CategoryEntity> level1Menus = categoryEntities.stream().filter(categoryEntitie ->
                categoryEntitie.getParentCid() == 0
        ).map(menu -> {
            menu.setChildren(getChildrens(menu, categoryEntities));
            return menu;
            // Comparator比较器
        }).sorted(Comparator.comparingInt(CategoryEntity::getSort)).collect(Collectors.toList());

        return level1Menus;
    }

    @Override
    public void removeMenusByIds(List<Long> ids) {
        // TODO 1、检查当前删除的菜单是否被别的地方引用
        baseMapper.deleteBatchIds(ids); // 物理删除


    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {

        List<Long> path = new ArrayList<>();
        List<Long> parentsPath = findParentsPath(catelogId, path);
        Collections.reverse(parentsPath);

        return path.toArray(new Long[parentsPath.size()]);
    }

    @Override
    @Transactional
    public void updateCascade(CategoryEntity category) {
        // 级联更新所有关联的数据
        // 更新自己
        this.updateById(category);

        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }

    private List<Long> findParentsPath(Long catelogId, List<Long> path){
        // 先把自己的id放到数组里
        // 然后判断自己是否有父
        // 如果有父，就找父是否有父
        // 如果有重复上面的过程
        path.add(catelogId);
        CategoryEntity categoryEntity = this.getById(catelogId);
        if(categoryEntity.getParentCid() != 0){
            findParentsPath(categoryEntity.getParentCid(), path);
        }

        return path;
    }

    // 递归查找 所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        return all.stream().filter(categoryEntity ->
                Objects.equals(categoryEntity.getParentCid(), root.getCatId())
        ).peek(menu -> {
            menu.setChildren(getChildrens(menu, all)); // 递归查找子菜单
        }).sorted(Comparator.comparing(CategoryEntity::getSort, Comparator.nullsFirst(Comparator.naturalOrder()))).collect(Collectors.toList());
    }
}