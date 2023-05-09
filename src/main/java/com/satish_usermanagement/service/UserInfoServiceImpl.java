package com.satish_usermanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.satish_usermanagement.entity.UserInfo;
import com.satish_usermanagement.repository.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserInfo createUser(UserInfo userInfo) {
		
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userInfo.setRole("ROLE_USER");
		return userInfoRepository.save(userInfo);
	}

	@Override
	public Boolean checkEmail(String email) {
		
		Boolean status = userInfoRepository.existsByEmail(email);
		return status;
	}

}
