package com.yonyou.cloud.tcc.points.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.points.api.entity.TmUser;

public interface PointsApi {

	@RequestMapping(value = "/points", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public void addPoints(@RequestBody TransactionEntity<TmUser> entity);
}
