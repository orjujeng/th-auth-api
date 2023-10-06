package com.orjujeng.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.orjujeng.auth.entity.AuthAccessInfo;
import com.orjujeng.auth.entity.AuthAccessMemberInfo;
import com.orjujeng.auth.entity.AuthInfo;

@Mapper
public interface AuthMapper {

	AuthInfo checkpasswordByMemberId(Integer memberId);

	AuthAccessInfo checkAuthAccess(Integer memberId);

	List<AuthAccessMemberInfo> getAllAuthAccessInfo();

}
