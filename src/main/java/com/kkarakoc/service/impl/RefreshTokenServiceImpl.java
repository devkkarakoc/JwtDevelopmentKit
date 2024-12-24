package com.kkarakoc.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kkarakoc.jwt.AuthResponse;
import com.kkarakoc.jwt.JwtService;
import com.kkarakoc.jwt.RefreshTokenRequest;
import com.kkarakoc.model.RefreshToken;
import com.kkarakoc.model.User;
import com.kkarakoc.repository.RefreshTokenRepository;
import com.kkarakoc.service.IRefreshTokenService;

@Service
public class RefreshTokenServiceImpl  implements IRefreshTokenService{

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	JwtService jwtService;
	
	public boolean isRefreshTokenExpired(Date expiredDate ) {
		return new Date().before(expiredDate);		
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setRefreshToken(UUID.randomUUID().toString() );
		refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
		refreshToken.setUser(user);
		
		return refreshToken;
	}
	
	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
	 Optional<RefreshToken>	refreshTokenOptional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if(refreshTokenOptional.isEmpty()) {
		System.out.println("Refresh Token Geçersiz : " + request.getRefreshToken());	
		
		}
		
		RefreshToken refreshToken = refreshTokenOptional.get();
		
		if(!isRefreshTokenExpired(refreshToken.getExpireDate())) {
		System.out.println("REFRESH TOKEN EXPİRE OLMUŞTUR :"+ request.getRefreshToken());	
		}
		
		String accesToken =  jwtService.generateToken(refreshToken.getUser());
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));
		AuthResponse newAuthResponse = new AuthResponse();
		newAuthResponse.setAccessToken(accesToken);
		newAuthResponse.setRefreshToken(savedRefreshToken.getRefreshToken());
		
		return newAuthResponse;
		
		
	}

}
