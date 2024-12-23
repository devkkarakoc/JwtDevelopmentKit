package com.kkarakoc.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		//bearer 1aafasfajlkfnhasf
		String header;
		String token;
		String username;
		
		
		 header = request.getHeader("Authorization");
		 
		 if(header==null) {
			 filterChain.doFilter(request, response);
			 return;
		 }
		 
       	token =	 header.substring(7);
		 try {
			
		 username = jwtService.getUsernameByToken(token);
			 if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
				 UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
				 if(userDetails!=null && jwtService.isTokenExpired(token)) {
					 //kişiyi security context e koyabiliriz
					 UsernamePasswordAuthenticationToken authentcation = 
							new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
					 		authentcation.setDetails(userDetails);
					 		
					 		SecurityContextHolder.getContext().setAuthentication(authentcation);
				 }
			 }
			 
			 
			 
			 
		} catch (ExpiredJwtException e) {
			System.out.println("Token Süresi Dolmuştur : " + e.getMessage());
		}
		 catch (Exception e) {
			 System.out.println("Genel Bir Hata Oluştu : " + e.getMessage());
		}
		 
		 
		 
		 
		
	}

	
	
}
