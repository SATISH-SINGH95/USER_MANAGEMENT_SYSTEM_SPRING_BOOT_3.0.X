package com.satish_usermanagement.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.satish_usermanagement.entity.UserInfo;
import com.satish_usermanagement.repository.UserInfoRepository;
import com.satish_usermanagement.utils.EmailService;

@Controller
public class ForgotPasswordController {
	
	public static int sentOtp;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private EmailService emailService;

	@PostMapping("/forgotPassword")
	public String forgotPassword(@RequestParam("email") String email, HttpSession session) {
		
		Random random = new Random();
		sentOtp = random.nextInt((9999 - 100) + 1) + 10;
	
		UserInfo user = userInfoRepository.findByEmail(email);
		
		if(user!=null) {
			
			emailService.sendEmail(email, "OTP", "Otp is "+ sentOtp);
			session.setAttribute("email", email);
			return "otppage";
		}
		else {
			session.setAttribute("msg", "User not found on server");
			return "forgot_password";
		}
	
	}
	
	@PostMapping("/verify-otp")
	public String verifyOTP(@RequestParam("otp") Integer otp, HttpSession session) {
		
		if(sentOtp==otp) {
			return "reset_password";
		}
		else {
			session.setAttribute("msg", "OTP is not matched");
			return "forgot_password";
		}
		
	}
	

	
}
