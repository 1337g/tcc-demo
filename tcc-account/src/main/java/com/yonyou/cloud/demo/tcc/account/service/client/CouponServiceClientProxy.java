package com.yonyou.cloud.demo.tcc.account.service.client;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.coupon.api.entity.TmCoupon;

@Component
public class CouponServiceClientProxy {

    @Autowired
    private CouponFeignClient couponFeignClient;

    @Compensable(propagation=Propagation.SUPPORTS, confirmMethod = "sendCoupon", cancelMethod = "sendCoupon")
    public RestResultResponse<TmCoupon> sendCoupon(TransactionContext context, TmCoupon tmCoupon) throws Exception {
        return couponFeignClient.sendCoupon(new TransactionEntity<TmCoupon>(context, tmCoupon));
    }
}
