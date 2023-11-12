package com.orjujeng.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orjujeng.auth.entity.AuthUpdate;
import com.orjujeng.auth.entity.LoginInfo;
import com.orjujeng.auth.server.AuthService;
import com.orjujeng.auth.utils.Result;
import com.orjujeng.auth.utils.ResultCode;

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

	@RequestMapping("/updateAuthInfo")
	public Result updateAuthInfo(@RequestBody AuthUpdate authUpdate) {
		if (authUpdate.getType() != null & authUpdate.getType().equals("authOfBackend")) {
			Result result = authService.updateAuthOfBackend(authUpdate);
			return result;
		} else {
			Result result = authService.updateAccessInfo(authUpdate);
			return result;
		}
	}

	@RequestMapping("/check")
	public Result checkAuthOfAll(@RequestParam(required = true) Integer memberId,@RequestParam(required = true) String type) {
		Result result= authService.checkAuthOfAll(memberId,type);
		return result;
	}
	
	@RequestMapping("/addAuthInfo")
	public Result addAuthInfo(@RequestParam(required = true) Integer memberId,@RequestParam(required = true) String accountNum) {
		Result result= authService.addNewAuthInfo(memberId,accountNum);
		return result;
	}
	
	@RequestMapping("/deleteAuthInfo")
	public Result deleteAuthInfo(@RequestParam(required = true) Integer memberId,@RequestParam(required = true) String accountNum) {
		Result result= authService.deleteAuthInfo(memberId,accountNum);
		return result;
	}
}
