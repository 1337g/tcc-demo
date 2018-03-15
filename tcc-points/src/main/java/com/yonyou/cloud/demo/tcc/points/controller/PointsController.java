package com.yonyou.cloud.demo.tcc.points.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.demo.tcc.points.service.PointsService;
import com.yonyou.cloud.demo.tcc.points.service.PointsServiceImpl;
import com.yonyou.cloud.tcc.account.api.entity.TmUser;
import com.yonyou.cloud.tcc.points.api.PointsApi;
import com.yonyou.cloud.tcc.points.api.entity.TmPoints;

@RestController
public class PointsController extends BaseController<PointsServiceImpl, TmPoints> implements PointsApi{

	@Autowired
	PointsService pointsService;
	
	@RequestMapping(value="/points",method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@YcApi
	public RestResultResponse<TmPoints> addPoints(@RequestBody TransactionEntity<TmUser> entity) {
		return new RestResultResponse<>().data(pointsService.addUserPoints(entity.getContext(),entity.getBody())).success(true);
	}
	
}
