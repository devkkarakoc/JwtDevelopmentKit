package com.kkarakoc.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;
import com.kkarakoc.model.User;
import com.kkarakoc.repository.UserRepository;
import com.kkarakoc.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public DtoUser register(AuthRequest request) {

		DtoUser dtoUser = new DtoUser();
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword( passwordEncoder.encode(request.getPassword()));

		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dtoUser);

		return dtoUser;
	}

}
