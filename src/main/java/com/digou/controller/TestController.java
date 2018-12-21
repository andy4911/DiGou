package com.digou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@ResponseBody
public class TestController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String requestMethodName() {
		return "this is index";
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello() {
		return "hello";
	}
}
