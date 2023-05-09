package com.satish_usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satish_usermanagement.entity.UserInfo;


public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{
	Boolean existsByEmail(String email);
	UserInfo findByEmail(String email);
	//UserInfo findByEmailAndMobileNumber(String email, String mobile);
}

