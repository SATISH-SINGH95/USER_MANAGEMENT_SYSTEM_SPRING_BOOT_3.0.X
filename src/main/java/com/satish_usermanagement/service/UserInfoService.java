package com.satish_usermanagement.service;

import com.satish_usermanagement.entity.UserInfo;

public interface UserInfoService {

	UserInfo createUser(UserInfo userInfo);
	
	Boolean checkEmail(String email);
	
}
