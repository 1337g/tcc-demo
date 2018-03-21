package com.yonyou.cloud.demo.tcc.notification.service;

import java.util.Date;

import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.Propagation;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.mengyun.tcctransaction.api.TransactionXid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yonyou.cloud.common.service.BaseService;
import com.yonyou.cloud.tcc.notification.api.entity.TmNotification;

import tk.mybatis.mapper.common.Mapper;

@Service
public class NotificationService extends BaseService<Mapper<TmNotification>, TmNotification>{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Compensable(propagation=Propagation.REQUIRES_NEW,confirmMethod="bindNotification",cancelMethod="unBindNotification")
	@Transactional(rollbackFor = Exception.class)
	public TmNotification tryBindNotification(TransactionContext transactionContext, TmNotification tmNotification) throws Exception{
    	System.out.println(TransactionXid.byteArrayToUUID(transactionContext.getXid().getGlobalTransactionId()).toString());
    	System.out.println(TransactionXid.byteArrayToUUID(transactionContext.getXid().getBranchQualifier()).toString());
		TmNotification notification = new TmNotification();
		notification.setId(tmNotification.getId());
		
		this.insert(notification);
		throw new Exception("part-exception");
//		return notification;
	}

	@Transactional(rollbackFor = Exception.class)
	public TmNotification bindNotification(TransactionContext transactionContext, TmNotification tmNotification) throws Exception{
		logger.info("零配件通知确认");
		System.out.println(TransactionXid.byteArrayToUUID(transactionContext.getXid().getGlobalTransactionId()).toString());
    	System.out.println(TransactionXid.byteArrayToUUID(transactionContext.getXid().getBranchQualifier()).toString());
		tmNotification.setUpdateDate(new Date());
		this.updateSelectiveById(tmNotification);
		return tmNotification;
	}

	@Transactional(rollbackFor = Exception.class)
	public TmNotification unBindNotification(TransactionContext transactionContext, TmNotification tmNotification) throws Exception{
		logger.info("取消零配件通知");
		tmNotification.setUpdateDate(new Date());
		this.updateById(tmNotification);
		return null;
	}

}
