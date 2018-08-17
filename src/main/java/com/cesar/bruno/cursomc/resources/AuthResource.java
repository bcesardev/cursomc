package com.cesar.bruno.cursomc.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesar.bruno.cursomc.dto.EmailDTO;
import com.cesar.bruno.cursomc.security.JWTUtil;
import com.cesar.bruno.cursomc.security.UserSS;
import com.cesar.bruno.cursomc.services.AuthService;
import com.cesar.bruno.cursomc.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthService service;

	@PostMapping("/refresh-token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/forgot")
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
