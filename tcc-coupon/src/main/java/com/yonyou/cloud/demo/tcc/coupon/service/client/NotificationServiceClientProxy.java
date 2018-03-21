package com.yonyou.cloud.demo.tcc.coupon.service.client;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.notification.api.entity.TmNotification;

@Component
public class NotificationServiceClientProxy {

    @Autowired
    private NotificationFeignClient notificationFeignClient;
    //propagation=Propagation.SUPPORTS,
    @Compensable(confirmMethod = "sendNotification", cancelMethod = "sendNotification")
    public RestResultResponse<TmNotification> sendNotification(TransactionContext context, TmNotification tmNotification) throws Exception {
    	return notificationFeignClient.sendNotification(new TransactionEntity<TmNotification>(context, tmNotification));
    }
}
