package com.orjujeng.auth.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {
	private int id;
	private int memberId;
	private String accountNum;
	private String password;
	private String termination;
	private Date lastUpdatedDate;
}
