package com.yonyou.cloud.demo.tcc.coupon.service;

import java.util.Date;
import java.util.Random;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
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
public class CouponNotificationService  extends BaseService<Mapper<TmCoupon>, TmCoupon>{

	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 

    @Autowired
    private NotificationServiceClientProxy notificationServiceClientProxy;
	
	@Compensable(confirmMethod="bindCouponNotification",cancelMethod="unBindCouponNotification")
	public TmCoupon tryBindCouponNotification(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{

		logger.info("优惠券通知尝试");
		Random r = new Random();
		long i=r.nextInt(301);
		TmCoupon coupon = new TmCoupon();
		coupon.setId(i);
		coupon.setCouponInfo("notification");
		this.insert(coupon);
		TmNotification tranEntity=new TmNotification();
		tranEntity.setId(tmCoupon.getId());
		notificationServiceClientProxy.sendNotification(transactionContext,tranEntity);
		
		return this.selectOne(coupon);
	}
	
	public TmCoupon bindCouponNotification(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{
		logger.info("优惠券通知确认");
		tmCoupon.setUpdateDate(new Date());
		this.updateSelectiveById(tmCoupon);
		return tmCoupon;
	}
	
	public TmCoupon unBindCouponNotification(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{
		logger.info("优惠券通知取消");
		tmCoupon.setUserId(null);
		tmCoupon.setUpdateDate(new Date());
		this.updateById(tmCoupon);
		return null;
	}
}
