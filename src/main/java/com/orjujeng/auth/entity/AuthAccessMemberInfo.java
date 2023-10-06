package com.orjujeng.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthAccessMemberInfo {
	private Integer aid;
	private Integer memberId;
	private String 	authAccess;
	private String  profileAccess;
	private String  timesheetAccess;
	private String  requestAccess;
	private MemberInfo memberInfo;
}
