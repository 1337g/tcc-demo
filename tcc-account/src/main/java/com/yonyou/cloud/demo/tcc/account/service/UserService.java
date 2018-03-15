package com.yonyou.cloud.demo.tcc.account.service;

import org.mengyun.tcctransaction.api.TransactionContext;

import com.yonyou.cloud.tcc.points.api.entity.TmUser;

public interface UserService {

	/**
	 * 用户注册 try阶段
	 * 
	 * @param userName
	 * @return
	 */
	public TmUser userSign(String userName) ;

	public TmUser confirmSign(String userName);
	
	public TmUser cancelSign(String userName);

}
