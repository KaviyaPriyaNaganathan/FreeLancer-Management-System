package com.freelancing.service;

import com.freelancing.dto.request.LoginRequestDTO;
import com.freelancing.dto.response.LoginResponseDTO;

public interface AuthService {

	LoginResponseDTO login(LoginRequestDTO dto);

}
