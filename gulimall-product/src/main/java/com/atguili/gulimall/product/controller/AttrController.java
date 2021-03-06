package com.atguili.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.R;
import com.atguili.gulimall.product.vo.AttrGroupRelationVo;
import com.atguili.gulimall.product.vo.AttrResponseVo;
import com.atguili.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.atguili.gulimall.product.entity.AttrEntity;
import com.atguili.gulimall.product.service.AttrService;



/**
 * 商品属性
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 12:28:21
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:attr:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    // 规则参数
    @RequestMapping("/{attrType}/list/{catelogId}")
    //@RequiresPermissions("product:attr:list")
    public R baseList(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId") Long catelogId,
                      @PathVariable("attrType") String type){
        PageUtils page = attrService.queryBasePage(params, catelogId, type);

        return R.ok().put("page", page);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    //@RequiresPermissions("product:attr:info")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);
        AttrResponseVo attrResponseVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", attrResponseVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attr:save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attr:update")
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attr:delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }
}
