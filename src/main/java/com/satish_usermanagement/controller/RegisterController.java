package com.satish_usermanagement.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.satish_usermanagement.entity.UserInfo;
import com.satish_usermanagement.service.UserInfoService;


@Controller
@RequestMapping("/")
public class RegisterController {
	
	@Autowired
	private UserInfoService userInfoService;

	@PostMapping("/create")
	public String createUser(@ModelAttribute UserInfo userInfo, HttpSession session) {
		
		Boolean status = userInfoService.checkEmail(userInfo.getEmail());
		
		if(status==true) {
			session.setAttribute("msg", "Email already exist on server !!");
		}
		else {
			UserInfo user = userInfoService.createUser(userInfo);
			if(user!=null) {
				session.setAttribute("msg", "User Registered Successfully !!");
			}
			else {
				session.setAttribute("msg", "Internal Server Error !!");
			}
		}
		
		return "redirect:/register";
	}

}
