package com.atguili.gulimall.member.feign;

import com.atguili.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("gulimall-coupon") // 注册中心中的名字
public interface CouponFeignService {
    @RequestMapping("/coupon/coupon/member/list") // 完整签名
    public R menbercoupons();  // 方法名
}
