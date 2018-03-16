package com.yonyou.cloud.tcc.coupon.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.coupon.api.entity.TmCoupon;

public interface CouponApi {

	@RequestMapping(value="/coupon",method=RequestMethod.POST)
	public RestResultResponse<TmCoupon> sendCoupon(@RequestBody TransactionEntity<TmCoupon> tranEntity) throws Exception;
	
}
