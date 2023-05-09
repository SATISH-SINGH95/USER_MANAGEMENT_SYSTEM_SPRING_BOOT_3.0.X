package com.satish_usermanagement.utils;

public interface EmailService {

	void sendEmail(String to, String subject, String text);
}
