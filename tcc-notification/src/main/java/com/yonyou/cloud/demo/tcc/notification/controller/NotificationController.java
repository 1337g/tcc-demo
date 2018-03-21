package com.yonyou.cloud.demo.tcc.notification.controller;

import org.mengyun.tcctransaction.api.TransactionXid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.demo.tcc.notification.service.NotificationService;
import com.yonyou.cloud.tcc.notification.api.NotificationApi;
import com.yonyou.cloud.tcc.notification.api.entity.TmNotification;

@RestController
public class NotificationController  extends BaseController<NotificationService, TmNotification> implements NotificationApi {
	
	@Autowired
	NotificationService notificationService;
	
	
//	@RequestMapping(value="/notification",method=RequestMethod.POST)
//	@YcApi
//	public RestResultResponse<TmNotification> sendNotification(@RequestBody TmNotification tranEntity) throws Exception{
//		return new RestResultResponse<>().success(true).data(notificationService.tryBindNotification(null, tranEntity));
//	}


	@RequestMapping(value="/notification",method=RequestMethod.POST)
	public RestResultResponse<TmNotification> sendNotification(@RequestBody TransactionEntity<TmNotification> tranEntity)
			throws Exception {
		tranEntity.getBody();
//		System.out.println(TransactionXid.byteArrayToUUID(tranEntity.getContext().getXid().getGlobalTransactionId()).toString());
//    	System.out.println(TransactionXid.byteArrayToUUID(tranEntity.getContext().getXid().getBranchQualifier()).toString());
		return new RestResultResponse<>().success(true).data(notificationService.tryBindNotification(tranEntity.getContext(), tranEntity.getBody()));
	}


//	@RequestMapping(value="/aloneNotification",method=RequestMethod.POST)
//	public RestResultResponse<TmNotification> sendAloneNotification(TmNotification tranEntity) throws Exception {
//		return new RestResultResponse<>().success(true).data(notificationService.tryBindNotification(null, tranEntity));
//	}
}
