package com.yonyou.cloud.demo.tcc.coupon.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.yonyou.cloud.tcc.notification.api.NotificationApi;

@FeignClient(name="TCC-NOTIFICATION")
public interface NotificationFeignClient extends NotificationApi{

}
