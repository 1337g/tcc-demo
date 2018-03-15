package com.yonyou.cloud.demo.tcc.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.cloud.common.annotation.YcApi;
import com.yonyou.cloud.common.beans.RestResultResponse;
import com.yonyou.cloud.common.controller.BaseController;
import com.yonyou.cloud.demo.tcc.account.service.UserService;
import com.yonyou.cloud.demo.tcc.account.service.UserServiceImpl;
import com.yonyou.cloud.tcc.account.api.entity.TmUser;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserServiceImpl, TmUser>{

	@Autowired
	private UserService userService;
	
	
	/**
	 * 注册之后发积分和优惠券
	 * 
	 * @param userName
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/sign",method=RequestMethod.GET)
	@YcApi
	public RestResultResponse<TmUser> loginUser(String userName) throws Exception{
		TmUser user = userService.userSign(userName);
		return new RestResultResponse().success(true).data(user);
		
	}
}
