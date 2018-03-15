package com.yonyou.cloud.demo.tcc.account.service;

import com.yonyou.cloud.tcc.account.api.entity.TmUser;

public interface UserService {

	/**
	 * 用户注册 try阶段
	 * 
	 * @param userName
	 * @return
	 */
	public TmUser userSign(String userName) throws Exception;

	public TmUser confirmSign(String userName) throws Exception;
	
	public TmUser cancelSign(String userName) throws Exception;

}
