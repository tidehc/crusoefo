package com.crusoe.fo.oauth.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizeController {
	@Autowired
	@Qualifier("consumerTokenServices")
	ConsumerTokenServices consumerTokenServices;

	@Autowired
	TokenEndpoint tokenEndpoint;

	@GetMapping("/user/logout")
	public String logout(@RequestParam String token) {
		if (consumerTokenServices.revokeToken(token)) {
			return "注销成功";
		} else {
			return "注销失败";
		}
	}

	/**
	 * 获取用户凭证（供客户端使用）
	 * 
	 * @param principal
	 * @return
	 */
	@GetMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
}
