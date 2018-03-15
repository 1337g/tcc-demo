package com.yonyou.cloud.demo.tcc.points.service;

import java.util.Date;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.api.Compensable.DefaultTransactionContextEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.demo.tcc.points.entity.TmPoints;
import com.yonyou.cloud.tcc.points.api.entity.TmUser;

import tk.mybatis.mapper.common.Mapper;

@Service
public class PointsServiceImpl extends BaseService<Mapper<TmPoints>, TmPoints> implements PointsService{
	
	private static final Logger logger = LoggerFactory.getLogger(PointsServiceImpl.class);
	
	/**
	 * 增加用户积分
	 * 
	 * @param context
	 * @param user
	 */
	@Compensable(confirmMethod = "confirmAdd", cancelMethod = "cancelAdd",transactionContextEditor = DefaultTransactionContextEditor.class)
	@Transactional(rollbackFor = Exception.class)
	public void addUserPoints(TransactionContext context, TmUser user) {
		
		logger.debug("getGlobalTransactionId "+context.getXid().getGlobalTransactionId().toString());
		
		TmPoints points = new TmPoints();
		points.setUserId(user.getId());
		points=this.selectOne(points);
		
		if(points!=null) {
			points.setValue(points.getValue()+5);
			points.setUpdateDate(new Date());
			this.updateSelectiveById(points);
		}else {
			TmPoints newPoints = new TmPoints();
			newPoints.setUserId(user.getId());
			newPoints.setCreateDate(new Date());
			newPoints.setValue(5L);
			this.insert(newPoints);
		}
		
		logger.info(""+1/0);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void confirmAdd(TransactionContext context, TmUser user) {
		logger.debug("确认提交积分");
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void cancelAdd(TransactionContext context, TmUser user) {
		logger.debug("取消提交积分");
	}
	
	
	
	
}
