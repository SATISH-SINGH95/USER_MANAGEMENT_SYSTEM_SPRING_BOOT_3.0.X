package com.satish_usermanagement.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.satish_usermanagement.entity.UserInfo;
import com.satish_usermanagement.repository.UserInfoRepository;


@Controller
@RequestMapping("/user")
public class UserController {
		
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@ModelAttribute
	private void getUserDetails(Model model, Principal principal) {
		String email = principal.getName();
		UserInfo user = userInfoRepository.findByEmail(email);
		model.addAttribute("user", user);
	}
	
	@GetMapping("/")
	public String userHome() {
		return "user/home";
	}
	
	@GetMapping("/changePasswordPage")
	public String changePasswordPage() {
		return "user/change_password";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(Principal principle, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword, HttpSession session) {
		
		String email = principle.getName();
		
		UserInfo loginUser = userInfoRepository.findByEmail(email);
		
		boolean isPasswordCorrect = passwordEncoder.matches(oldPassword, loginUser.getPassword());
		
		
		if(isPasswordCorrect==true) {
			
			loginUser.setPassword(passwordEncoder.encode(newPassword));  // new Password is satishsingh---> satish
			UserInfo userPasswordUpdated = userInfoRepository.save(loginUser);
			if(userPasswordUpdated!=null) {
				session.setAttribute("msg", "Password changed Successfully !!");
			}else {
				session.setAttribute("msg", "Something went wrong on server !!");
			}
			
		}else {
			session.setAttribute("msg", "Old password incorrect !!");
		}
		
		return "redirect:/user/changePasswordPage";
	}
		
}
