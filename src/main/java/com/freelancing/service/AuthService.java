package com.freelancing.service;

import com.freelancing.dto.request.LoginRequestDTO;
import com.freelancing.dto.response.LoginResponseDTO;

public interface AuthService {

	LoginResponseDTO login(LoginRequestDTO dto);

	//String forgotPassword(String email);

	//boolean resetPassword(String token, String newPassword);
	 String generateResetToken(String email);
	    String resetPassword(String token, String newPassword);

}

