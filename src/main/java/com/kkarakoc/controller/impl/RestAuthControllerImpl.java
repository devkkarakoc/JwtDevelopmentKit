package com.kkarakoc.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kkarakoc.controller.IRestAuthController;
import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;
import com.kkarakoc.service.IAuthService;

import jakarta.validation.Valid;


@RestController
public class RestAuthControllerImpl implements IRestAuthController {

	@Autowired
	IAuthService authService;
	
	@PostMapping("/register")
	@Override
	public DtoUser register(@Valid @RequestBody AuthRequest request) {
		// TODO Auto-generated method stub
		return authService.register(request);
	}

	
	
}
