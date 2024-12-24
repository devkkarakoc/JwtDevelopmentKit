package com.kkarakoc.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.kkarakoc.dto.DtoUser;
import com.kkarakoc.jwt.AuthRequest;
import com.kkarakoc.jwt.AuthResponse;
import com.kkarakoc.jwt.JwtService;
import com.kkarakoc.model.User;
import com.kkarakoc.repository.UserRepository;
import com.kkarakoc.service.IAuthService;

@Service
public class AuthServiceImpl implements IAuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtService jwtService;

	@Override
	public AuthResponse authenticate(AuthRequest request) {

		try {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(request.getUsername(),
					request.getPassword());

			authenticationProvider.authenticate(auth);
			
		  Optional<User> optionalUser =	userRepository.findByUsername(request.getUsername());
		String token = jwtService.generateToken(optionalUser.get());
			
	
			return new AuthResponse(token);
		} catch (Exception e) {
			System.out.println("Kullanıcı Adı Veya Şifre Hatalı ");
		}

		return null;
	}

	public DtoUser register(AuthRequest request) {

		DtoUser dtoUser = new DtoUser();
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		User savedUser = userRepository.save(user);
		BeanUtils.copyProperties(savedUser, dtoUser);

		return dtoUser;
	}

}
