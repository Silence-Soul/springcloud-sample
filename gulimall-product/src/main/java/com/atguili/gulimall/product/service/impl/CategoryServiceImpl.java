package com.atguili.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.Query;

import com.atguili.gulimall.product.dao.CategoryDao;
import com.atguili.gulimall.product.entity.CategoryEntity;
import com.atguili.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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
        ).map(menu->{
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

    // 递归查找 所有菜单的子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all){

        List<CategoryEntity> children = all.stream().filter(categoryEntity ->
            categoryEntity.getParentCid() == root.getCatId()
        ).map(menu -> {
            menu.setChildren(getChildrens(menu, all)); // 递归查找子菜单
            return menu;
        }).sorted(Comparator.comparing(CategoryEntity::getSort, Comparator.nullsFirst(Comparator.naturalOrder()))).collect(Collectors.toList());

        return children;

    }
}