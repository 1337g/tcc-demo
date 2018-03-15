package com.yonyou.cloud.demo.tcc.points.service;

import org.mengyun.tcctransaction.api.TransactionContext;

import com.yonyou.cloud.tcc.points.api.entity.TmUser;

public interface PointsService {
	
	
	/**
	 * 增加用户积分
	 * 
	 * @param context
	 * @param user
	 */
	public void addUserPoints(TransactionContext context, TmUser user);
	
	public void confirmAdd(TransactionContext context, TmUser user);
	
	public void cancelAdd(TransactionContext context, TmUser user) ;
	
	
	
	
}
