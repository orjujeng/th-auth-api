package com.orjujeng.auth.feign;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.auth.entity.MemberInfo;
import com.orjujeng.auth.utils.Result;


@FeignClient(name="th-timesheet-api",url="${lb_url}")
public interface ProfileApiFeignService {
	@GetMapping("/profile/member/getMember")
	public Result getMemberInfoByAccountNum(@RequestParam(required = false, value = "accountNum") String accountNum);
	
	@PostMapping("/profile/member/updateMember")
	public Result updateMemberInfoByAccountNum(@Valid @RequestBody MemberInfo memberInfo);
}
