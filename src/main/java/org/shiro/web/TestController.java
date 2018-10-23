package org.shiro.web;


import org.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class TestController {
	@Autowired
	private UserService u;
	@RequestMapping("/index")
	public String dd(){
        u.login();
		return "index";
	}
	@RequestMapping("/404")
	public String un(){

		return "404";
	}
}
