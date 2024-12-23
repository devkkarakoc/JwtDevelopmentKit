package com.kkarakoc.service;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;

public interface IAuthService {
	
	public DtoUser register(AuthRequest request);

}
