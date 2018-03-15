package com.yonyou.cloud.demo.tcc.account.service;

import java.util.Date;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.demo.tcc.account.mapper.TmUserMapper;
import com.yonyou.cloud.demo.tcc.account.service.client.PointServiceClientProxy;
import com.yonyou.cloud.tcc.points.api.entity.TmUser;

import tk.mybatis.mapper.common.Mapper;

@Service
public class UserServiceImpl extends BaseService<Mapper<TmUser>, TmUser> implements UserService{

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	TmUserMapper tmUserMapper;

	@Autowired
	PointServiceClientProxy pointServiceClientProxy;

	/**
	 * 用户注册 try阶段
	 * 
	 * @param userName
	 * @return
	 */
	@Compensable(confirmMethod = "confirmSign", cancelMethod = "cancelSign")
	@Transactional(rollbackFor = Exception.class)
	public TmUser userSign(String userName) {
		TmUser user = new TmUser();
		user.setName(userName);
		user.setPassword("1");
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());
		this.insert(user);

		pointServiceClientProxy.addPoints(null, user);

//		int i = 1/0;
		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public TmUser confirmSign(String userName) {

		logger.debug("confirm 阶段 userName=" + userName);

		TmUser user = new TmUser();
		user.setName(userName);
		user = this.selectOne(user);

		return user;
	}

	@Transactional(rollbackFor = Exception.class)
	public TmUser cancelSign(String userName) {
		logger.debug("cancel 阶段 userName=" + userName);

		TmUser user = new TmUser();
		user.setName(userName);
		this.delete(user);

		return null;
	}

}