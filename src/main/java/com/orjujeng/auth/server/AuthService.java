package com.orjujeng.auth.server;

import org.springframework.stereotype.Service;

import com.orjujeng.auth.entity.AuthUpdate;
import com.orjujeng.auth.entity.LoginInfo;
import com.orjujeng.auth.utils.Result;
@Service
public interface AuthService {

	Result vaildLoginInfo(LoginInfo logininfo);

	void checkAuth(Integer memberId);

	Result getAuthList();

	Result updateAuthOfBackend(AuthUpdate authUpdate);

	Result updateAccessInfo(AuthUpdate authUpdate);

	Result checkAuthOfAll(Integer memberId, String type);

	Result addNewAuthInfo(Integer memberId, String accountNum);

	Result deleteAuthInfo(Integer memberId, String accountNum);

}
