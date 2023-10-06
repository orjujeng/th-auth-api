package com.orjujeng.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.orjujeng.auth.utils.Result;

@FeignClient("TH-ProfileApi")
public interface ProfileApiFeignService {
	@GetMapping("/profile/member/getMember")
	public Result getMemberInfoByAccountNum(@RequestParam(required = false, value = "accountNum") String accountNum);
}
