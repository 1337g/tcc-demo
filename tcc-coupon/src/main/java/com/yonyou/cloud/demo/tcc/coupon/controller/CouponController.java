package com.yonyou.cloud.demo.tcc.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.demo.tcc.coupon.service.CouponService;
import com.yonyou.cloud.tcc.coupon.api.CouponApi;
import com.yonyou.cloud.tcc.coupon.api.entity.TmCoupon;

@RestController
public class CouponController  extends BaseController<CouponService, TmCoupon> implements CouponApi {
	
	@Autowired
	CouponService couponService;
	
	
	@RequestMapping(value="/coupon",method=RequestMethod.POST)
	@YcApi
	public RestResultResponse<TmCoupon> sendCoupon(@RequestBody TransactionEntity<TmCoupon> tranEntity) throws Exception{
		return new RestResultResponse<>().success(true).data(couponService.tryBindCoupon(tranEntity.getContext(), tranEntity.getBody()));
	}
}
