package com.atguili.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.R;
import com.atguili.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.atguili.gulimall.product.entity.AttrEntity;
import com.atguili.gulimall.product.service.AttrAttrgroupRelationService;
import com.atguili.gulimall.product.service.AttrService;
import com.atguili.gulimall.product.service.CategoryService;
import com.atguili.gulimall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguili.gulimall.product.entity.AttrGroupEntity;
import com.atguili.gulimall.product.service.AttrGroupService;



/**
 * 属性分组
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 12:28:21
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    // 三级分类的 service
    @Autowired
    private CategoryService categoryService;

    @Autowired
    AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;

    // 分组关联
    @PostMapping("/attr/relation")
    public R addArrtRelation(@RequestBody List<AttrGroupRelationVo> vos){
        attrAttrgroupRelationService.saveBatch(vos);

        return R.ok();

    }

    // 分组关联
    @GetMapping("/{attrgroupId}/attr/relation")
    public R arrtRelation(@PathVariable("attrgroupId") Long attrgroupId){
        List<AttrEntity> arrtRelations = attrService.getRelationAttr(attrgroupId);

        return R.ok().put("data", arrtRelations);

    }

    // 没有分组关联
    @GetMapping("/{attrgroupId}/noattr/relation")
    public R arrtNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String, Object> params){
        PageUtils arrtRelations = attrService.getNoRelationAttr(params, attrgroupId);

        return R.ok().put("page", arrtRelations);

    }
    /**
     * 列表
     */
    @RequestMapping("/list/{catalogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,
                  @PathVariable Long catalogId){
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, catalogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long[] ids = categoryService.findCatelogPath(attrGroup.getCatelogId());
        attrGroup.setCatelogPath(ids);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] attrGroupRelationVos){
        attrService.deleteRelation(attrGroupRelationVos);
        return R.ok();
    }
}
