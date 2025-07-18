package com.auth.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
    
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	  String authHeader = request.getHeader("Authorization");
	  
	  String token = null;
	  String username = null;
	  
	  if(authHeader != null && authHeader.startsWith("Bearer ")) {
		  token = authHeader.substring(7);
		  username = jwtService.extractUserName(token);
	  }
	  
	  if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		  UserDetails userDetails = detailsServiceImpl.loadUserByUsername(username);
		  Boolean validateToken = jwtService.validateToken(token, userDetails);
		  if(validateToken) {
			UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());  
			authtoken.setDetails(new WebAuthenticationDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authtoken);
		  }
	  }
	  
	  filterChain.doFilter(request, response);
		
	}

}
