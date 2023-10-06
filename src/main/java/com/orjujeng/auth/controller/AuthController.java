package com.orjujeng.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orjujeng.auth.entity.LoginInfo;
import com.orjujeng.auth.server.AuthService;
import com.orjujeng.auth.utils.Result;

@RequestMapping("/auth")
@RestController
public class AuthController {
	@Autowired
	AuthService authService;
	@RequestMapping("/vaildLogin")
	public Result vaildLogin(@RequestBody LoginInfo logininfo) {
		Result result = authService.vaildLoginInfo(logininfo);
		return result;
	}
	
	@RequestMapping("/getAuthList")
	public Result getAuthList(@RequestParam(required = true) Integer memberId) {
		authService.checkAuth(memberId);
		Result result = authService.getAuthList();
		return result;
	}
}
