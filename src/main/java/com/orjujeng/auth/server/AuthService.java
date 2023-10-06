package com.orjujeng.auth.server;

import org.springframework.stereotype.Service;

import com.orjujeng.auth.entity.LoginInfo;
import com.orjujeng.auth.utils.Result;
@Service
public interface AuthService {

	Result vaildLoginInfo(LoginInfo logininfo);

	void checkAuth(Integer memberId);

	Result getAuthList();

}