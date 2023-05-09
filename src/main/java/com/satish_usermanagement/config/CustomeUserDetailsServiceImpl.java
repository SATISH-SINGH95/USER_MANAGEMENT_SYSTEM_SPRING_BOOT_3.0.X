package com.satish_usermanagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.satish_usermanagement.entity.UserInfo;
import com.satish_usermanagement.repository.UserInfoRepository;

@Service
public class CustomeUserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserInfo userInfo = userInfoRepository.findByEmail(email);
		
		if(userInfo!=null) {
			return new CustomUserDetails(userInfo);
		}
		
		throw new UsernameNotFoundException("User not found on server !!");
	}

}
