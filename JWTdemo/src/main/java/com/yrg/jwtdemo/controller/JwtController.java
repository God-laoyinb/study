package com.yrg.jwtdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtController {

	@RequestMapping("/hello")
	public String hello() {
		
		return "hello world";
	}
	@RequestMapping("/getuser")
	public String getuser() {
		return "usrname";
	}
}
