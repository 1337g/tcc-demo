package com.yonyou.cloud.demo.tcc.coupon.service;

import java.util.Date;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.tcc.coupon.api.entity.TmCoupon;

import tk.mybatis.mapper.common.Mapper;

@Service
public class CouponService extends BaseService<Mapper<TmCoupon>, TmCoupon>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Compensable(confirmMethod="bindCoupon",cancelMethod="unBindCoupon")
	public TmCoupon tryBindCoupon(TransactionContext transactionContext, TmCoupon tmCoupon) throws Exception{
		TmCoupon coupon = new TmCoupon();
		coupon.setId(tmCoupon.getId());
		
		
		
		return this.selectOne(coupon);
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
