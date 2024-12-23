package com.kkarakoc.controller;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;

public interface IRestAuthController {

	
	public DtoUser register(AuthRequest request);
}
