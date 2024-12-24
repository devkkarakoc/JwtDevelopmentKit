package com.kkarakoc.controller;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;
import com.kkarakoc.jwt.AuthResponse;
import com.kkarakoc.jwt.RefreshTokenRequest;

public interface IRestAuthController {

	
	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
