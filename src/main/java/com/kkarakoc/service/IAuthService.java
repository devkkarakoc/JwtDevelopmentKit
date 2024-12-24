package com.kkarakoc.service;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;
import com.kkarakoc.jwt.AuthResponse;

public interface IAuthService {
	
	public DtoUser register(AuthRequest request);
	public  AuthResponse authenticate(AuthRequest request);

}
