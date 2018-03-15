package com.yonyou.cloud.demo.tcc.account.service.client;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.yonyou.cloud.tcc.points.api.PointsApi;

@FeignClient(name="TCC-POINTS")
public interface PointsFeignClient extends PointsApi{

//	@RequestMapping(value = "/points", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//	public void addPoints(@RequestBody TransactionEntity<TmUser> entity);
	
}
