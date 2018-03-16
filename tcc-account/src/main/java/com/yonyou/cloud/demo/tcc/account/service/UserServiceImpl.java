package com.yonyou.cloud.demo.tcc.account.service;

import java.util.Date;

import org.mengyun.tcctransaction.api.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.demo.tcc.account.mapper.TmUserMapper;
import com.yonyou.cloud.demo.tcc.account.service.client.CouponServiceClientProxy;
import com.yonyou.cloud.demo.tcc.account.service.client.PointServiceClientProxy;
import com.yonyou.cloud.tcc.account.api.entity.TmUser;
import com.yonyou.cloud.tcc.coupon.api.entity.TmCoupon;
import com.yonyou.cloud.tcc.points.api.entity.TmPoints;

import tk.mybatis.mapper.common.Mapper;

@Service
public class UserServiceImpl extends BaseService<Mapper<TmUser>, TmUser> implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	TmUserMapper tmUserMapper;

	@Autowired
	PointServiceClientProxy pointServiceClientProxy;
	
	@Autowired
	CouponServiceClientProxy couponServiceClientProxy;

	/**
	 * 用户注册 try阶段
	 * 
	 * @param userName
	 * @return
	 * @throws Exception 
	 */
	@Compensable(confirmMethod = "confirmSign", cancelMethod = "cancelSign")
	@Transactional(rollbackFor = Exception.class)
	public TmUser userSign(String userName) throws Exception {
		TmUser user = new TmUser();
		user.setName(userName);
		user.setPassword("1");
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		this.insert(user);

		TmPoints points = pointServiceClientProxy.addPoints(null, user).getData();
		
		if(points==null) {
			throw new Exception("积分获取失败");
		}
		
		
		TmCoupon coupon = new TmCoupon();
		coupon.setId(1L);
		coupon.setUserId(user.getId());
		
		coupon=couponServiceClientProxy.sendCoupon(null, coupon).getData();
		
		if(coupon==null) {
			throw new Exception("优惠券发放失败");
		}

		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public TmUser confirmSign(String userName) throws Exception{

		logger.debug("confirm 阶段 userName=" + userName);

		TmUser user = new TmUser();
		user.setName(userName);
		user = this.selectOne(user);

		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public TmUser cancelSign(String userName) throws Exception{
		logger.debug("cancel 阶段 userName=" + userName);

		TmUser user = new TmUser();
		user.setName(userName);
		this.delete(user);

		return null;
	}

}
