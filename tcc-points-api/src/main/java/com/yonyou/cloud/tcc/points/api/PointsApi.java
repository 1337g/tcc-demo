package com.yonyou.cloud.tcc.points.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.tcc.account.api.entity.TmUser;
import com.yonyou.cloud.tcc.points.api.entity.TmPoints;

public interface PointsApi {

	@RequestMapping(value = "/points", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public RestResultResponse<TmPoints> addPoints(@RequestBody TransactionEntity<TmUser> entity);
}
