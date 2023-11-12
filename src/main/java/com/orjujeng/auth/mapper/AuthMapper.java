package com.orjujeng.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.orjujeng.auth.entity.AuthAccessInfo;
import com.orjujeng.auth.entity.AuthAccessMemberInfo;
import com.orjujeng.auth.entity.AuthCheckResult;
import com.orjujeng.auth.entity.AuthInfo;
import com.orjujeng.auth.entity.AuthUpdate;

@Mapper
public interface AuthMapper {

	AuthInfo checkpasswordByMemberId(Integer memberId);

	AuthAccessInfo checkAuthAccess(Integer memberId);

	List<AuthAccessMemberInfo> getAllAuthAccessInfo();

	void updateAuthAccessInfo(AuthUpdate authUpdate);

	AuthCheckResult checkAccessAccordingToType(@Param("memberId")Integer memberId,@Param("type") String type);

	void addAuthAccessInfo(@Param("memberId") Integer memberId);

	void addAuthInfo(@Param("memberId") Integer memberId,@Param("accountNum")String accountNum);

	void deleteAuthAccessInfo(Integer memberId);

	void deleteAuthInfo(@Param("memberId") Integer memberId,@Param("accountNum") String accountNum);

}
