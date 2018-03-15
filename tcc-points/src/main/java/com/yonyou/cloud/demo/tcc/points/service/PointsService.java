package com.yonyou.cloud.demo.tcc.points.service;

import org.mengyun.tcctransaction.api.TransactionContext;

import com.yonyou.cloud.tcc.account.api.entity.TmUser;
import com.yonyou.cloud.tcc.points.api.entity.TmPoints;

public interface PointsService {
	
	
	/**
	 * 增加用户积分
	 * 
	 * @param context
	 * @param user
	 */
	public TmPoints addUserPoints(TransactionContext context, TmUser user);
	
	public TmPoints confirmAdd(TransactionContext context, TmUser user);
	
	public TmPoints cancelAdd(TransactionContext context, TmUser user) ;
	
	
	
}
