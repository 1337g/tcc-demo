package com.yonyou.cloud.demo.tcc.points.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.tcc.TransactionEntity;
import com.yonyou.cloud.demo.tcc.points.service.PointsService;
import com.yonyou.cloud.tcc.points.api.PointsApi;
import com.yonyou.cloud.tcc.points.api.entity.TmUser;

@RestController
public class PointsController implements PointsApi{

	@Autowired
	PointsService pointsService;
	
	@RequestMapping(value="/points",method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public void addPoints(@RequestBody TransactionEntity<TmUser> entity) {
		pointsService.addUserPoints(entity.getContext(),entity.getBody());
	}
	
}
