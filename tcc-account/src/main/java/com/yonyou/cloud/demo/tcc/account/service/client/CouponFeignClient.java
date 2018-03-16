package com.yonyou.cloud.demo.tcc.account.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.yonyou.cloud.tcc.coupon.api.CouponApi;

@FeignClient(name="TCC-COUPON")
public interface CouponFeignClient extends CouponApi{

}
