package com.atguili.gulimall.product;

import com.atguili.gulimall.product.entity.BrandEntity;
import com.atguili.gulimall.product.service.BrandService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@SpringBootTest
class GulimallProductApplicationTests {

    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {

        BrandEntity brandEntity = new BrandEntity();
//		brandEntity.setDescript("11111");
//		brandEntity.setName("华为");
//		brandService.save(brandEntity);
//
//		System.out.println("保存成功");

//
//		brandEntity.setBrandId(1L);
//		brandEntity.setDescript("修改描述");
//		brandService.updateById(brandEntity);


        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
        list.forEach(brandEntity1 -> {
            System.out.println(brandEntity1);
        });

        System.out.println("修改成功");
    }

}
