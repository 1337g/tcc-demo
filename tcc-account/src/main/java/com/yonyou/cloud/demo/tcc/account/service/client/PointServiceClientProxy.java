package com.yonyou.cloud.demo.tcc.account.service.client;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.points.api.entity.TmUser;

@Component
public class PointServiceClientProxy {

    @Autowired
    private PointsFeignClient pointsFeignClient;

    /**
     * HTTP 需要多使用一个中间层调用远程服务，以便让事务拦截器记录参与者
     *
     * @param context
     * @param user
     * @return
     */
    @Compensable(propagation=Propagation.SUPPORTS, confirmMethod = "addPoints", cancelMethod = "addPoints")
    public void addPoints(TransactionContext context, TmUser user) {
        pointsFeignClient.addPoints(new TransactionEntity<>(context, user));
    }
}
