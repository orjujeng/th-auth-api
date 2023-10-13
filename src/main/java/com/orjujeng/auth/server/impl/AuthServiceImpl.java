package com.orjujeng.auth.server.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orjujeng.auth.entity.AuthAccessInfo;
import com.orjujeng.auth.entity.AuthAccessMemberInfo;
import com.orjujeng.auth.entity.AuthCheckResult;
import com.orjujeng.auth.entity.AuthInfo;
import com.orjujeng.auth.entity.AuthUpdate;
import com.orjujeng.auth.entity.LoginInfo;
import com.orjujeng.auth.entity.MemberInfo;
import com.orjujeng.auth.exception.AccessDenideException;
import com.orjujeng.auth.exception.AuthAccessDeniedException;
import com.orjujeng.auth.feign.ProfileApiFeignService;
import com.orjujeng.auth.mapper.AuthMapper;
import com.orjujeng.auth.server.AuthService;
import com.orjujeng.auth.utils.Result;
import com.orjujeng.auth.utils.ResultCode;
@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	ProfileApiFeignService profileApiFeignService;
	@Autowired
	AuthMapper authMapper;
	
	public Result vaildLoginInfo(LoginInfo logininfo) {
		String accountNum = logininfo.getAccountNum();
		Result memberInfoByAccountNum = profileApiFeignService.getMemberInfoByAccountNum(accountNum);
		if(memberInfoByAccountNum.getCode().equals(ResultCode.SUCCESS.code)){
			String s = JSON.toJSONString(memberInfoByAccountNum.getData());
			List<MemberInfo> memberInfo = JSONObject.parseArray(s, MemberInfo.class);
			if(memberInfo == null || memberInfo.size()==0) {
				return Result.error(ResultCode.PASSWORD_ERROR.code, "Password Error", null);
			}
			String deleteFlag = memberInfo.get(0).getDeleteFlag();
			String authOfBackend = memberInfo.get(0).getAuthOfBackend();
			Date expiredDate = memberInfo.get(0).getExpiredDate();
			Date now = new Date();
			Boolean isExpired = expiredDate.before(now);
			if(deleteFlag != null && deleteFlag.equals("N") && isExpired == false) {
				if(authOfBackend!= null && authOfBackend.equals("Y")) {
					Integer memberId = memberInfo.get(0).getId();
					AuthInfo authInfo= authMapper.checkpasswordByMemberId(memberId);
					String encodePw = DigestUtils.md5Hex(logininfo.getPassword());
					if(authInfo.getPassword() !=null && authInfo.getPassword().equals(encodePw)) {
						return Result.success(memberInfo.get(0));
					}else {
						return Result.error(ResultCode.PASSWORD_ERROR.code, "Password Error", null);
					}
				}else {
					throw new AccessDenideException("Access Denide");
				}
			}else {
				throw new AccessDenideException("Access Denide");
			}
		}
		return Result.error(ResultCode.INTERNAL_SERVER_ERROR.code, null, null);
	}

	public void checkAuth(Integer memberId) {
		AuthAccessInfo authAccessInfo =authMapper.checkAuthAccess(memberId);
		if(authAccessInfo==null || authAccessInfo.getAuthAccess().equals("N")) {
			throw new AuthAccessDeniedException("Auth Access Denied");
		}
	}
	
	@Cacheable(value = "AUTH",key = "#root.methodName")
	public Result getAuthList() {
		List<AuthAccessMemberInfo> result = authMapper.getAllAuthAccessInfo();
		return Result.success(result);
	}

	@Override
	@CacheEvict(value = "AUTH",key = "'getAuthList'")
	public Result updateAuthOfBackend(AuthUpdate authUpdate) {
		MemberInfo input = new MemberInfo();
		input.setAccountNum(authUpdate.getAccountNum());
		input.setAuthOfBackend(authUpdate.getValue());
		Result result = profileApiFeignService.updateMemberInfoByAccountNum(input);
		return result;
	}

	@Override
	@Transactional
	@CacheEvict(value = "AUTH",key = "'getAuthList'")
	public Result updateAccessInfo(AuthUpdate authUpdate) {
		authMapper.updateAuthAccessInfo(authUpdate);
		return Result.success(null);
	}

	@Override
	public Result checkAuthOfAll(Integer memberId, String type) {
		AuthCheckResult result = authMapper.checkAccessAccordingToType(memberId,type);
		if(result==null || result.getResult().equals("N")) {
			throw new AuthAccessDeniedException("Access Denied");
		}
		return Result.success(null);
	}

}
