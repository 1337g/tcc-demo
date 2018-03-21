package com.yonyou.cloud.tcc.notification.api;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.notification.api.entity.TmNotification;

public interface NotificationApi {

	@RequestMapping(value="/notification",method=RequestMethod.POST)
	public RestResultResponse<TmNotification> sendNotification(@RequestBody TransactionEntity<TmNotification> tranEntity) throws Exception;
	
//	@RequestMapping(value="/aloneNotification",method=RequestMethod.POST)
//	public RestResultResponse<TmNotification> sendAloneNotification(@RequestBody TmNotification tranEntity) throws Exception;
	
	
	
}
