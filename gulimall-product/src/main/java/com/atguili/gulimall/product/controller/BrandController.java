package com.atguili.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.atguili.common.valid.AddGroup;
import com.atguili.common.valid.UpdateGroup;
import com.atguili.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguili.gulimall.product.entity.BrandEntity;
import com.atguili.gulimall.product.service.BrandService;
import com.atguili.common.utils.PageUtils;
import com.atguili.common.utils.R;


/**
 * 品牌
 *
 * @author lzy
 * @email lzyboy521@gmail.com
 * @date 2021-08-06 12:28:21
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("product:brand:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    //@RequiresPermissions("product:brand:info")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:brand:save")
    // @Validated 校验注解
    public R save(@Validated({AddGroup.class})
                  @RequestBody BrandEntity brand) {
//    public R save(@Valid @RequestBody BrandEntity brand, BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            Map<String, String> map = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(fieldError -> {
//                String defaultMessage = fieldError.getDefaultMessage(); //错误提示消息
//                String fieldName = fieldError.getField(); // 错误属性名
//                map.put(fieldName, defaultMessage);
//            });
//            return R.error(400, "数据不合法").put("data", map);
//        } else {
//            brandService.save(brand);
//            return R.ok();
//        }

        brandService.save(brand);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:brand:update")
    public R update(@Validated({UpdateGroup.class})
                    @RequestBody BrandEntity brand) {
//        brandService.updateById(brand);
        brandService.updateDetail(brand);

        return R.ok();
    }

    /**
     * 修改品牌状态
     */
    @RequestMapping("/update/status")
    //@RequiresPermissions("product:brand:update")
    public R updateStatus(@Validated({UpdateStatusGroup.class})
                          @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:brand:delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
