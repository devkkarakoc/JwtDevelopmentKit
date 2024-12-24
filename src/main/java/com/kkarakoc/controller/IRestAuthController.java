package com.kkarakoc.controller;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;
import com.kkarakoc.jwt.AuthResponse;

public interface IRestAuthController {

	
	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
}
