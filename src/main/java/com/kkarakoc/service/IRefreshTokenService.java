package com.kkarakoc.service;

import com.kkarakoc.jwt.AuthResponse;
import com.kkarakoc.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {
	
	public AuthResponse refreshToken(RefreshTokenRequest request);

}
