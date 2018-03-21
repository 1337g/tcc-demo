package com.yonyou.cloud.demo.tcc.coupon.service;

import java.util.Date;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.api.TransactionXid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.demo.tcc.coupon.service.client.NotificationServiceClientProxy;
import com.yonyou.cloud.tcc.coupon.api.entity.TmCoupon;
import com.yonyou.cloud.tcc.notification.api.entity.TmNotification;

import tk.mybatis.mapper.common.Mapper;

@Service
public class CouponService extends BaseService<Mapper<TmCoupon>, TmCoupon>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

    @Autowired
    private CouponNotificationService couponNotificationService;
//
    @Autowired
    private NotificationServiceClientProxy notificationServiceClientProxy;
	
	@Compensable(confirmMethod="bindCoupon",cancelMethod="unBindCoupon")
	public TmCoupon tryBindCoupon(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{
		logger.info("确认优惠券尝试");
		System.out.println("--tid,"+TransactionXid.byteArrayToUUID(transactionContext.getXid().getGlobalTransactionId()).toString());
    	System.out.println("--tid,"+TransactionXid.byteArrayToUUID(transactionContext.getXid().getBranchQualifier()).toString());
		
		TmCoupon coupon = new TmCoupon();
		coupon.setId(tmCoupon.getId());
//		coupon.setCouponInfo(tmCoupon.getCouponInfo());
		coupon.setCreateDate(new Date());
		coupon.setUpdateDate(new Date());
		this.insert(coupon);

		try{

			TmNotification tranEntity=new TmNotification();
			tranEntity.setId(tmCoupon.getId());
			notificationServiceClientProxy.sendNotification(null,tranEntity);
		}catch(Exception e){
			
		}
//		TmCoupon c=this.selectOne(coupon);
		
		return coupon;
	}
	
	public TmCoupon bindCoupon(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{
		logger.info("确认优惠券发放");
		tmCoupon.setUpdateDate(new Date());
		this.updateSelectiveById(tmCoupon);
		return tmCoupon;
	}
	
	public TmCoupon unBindCoupon(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{
		logger.info("取消发放优惠券");
		tmCoupon.setUserId(null);
		tmCoupon.setUpdateDate(new Date());
		this.updateById(tmCoupon);
		return null;
	}

}
