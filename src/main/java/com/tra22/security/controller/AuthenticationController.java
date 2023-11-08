package com.tra22.security.controller;

import com.tra22.security.payload.global.Response;
import com.tra22.security.payload.request.AuthenticationRequest;
import com.tra22.security.payload.request.RegisterRequest;
import com.tra22.security.service.interf.IAuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
  private final IAuthenticationService service;
  @PostMapping("/register")
  public ResponseEntity<Response<?>> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(Response.ok(service.register(request)));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<Response<?>> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(Response.ok(service.authenticate(request)));
  }
  @PostMapping("/refresh-token")
  public ResponseEntity<Response<?>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
    return ResponseEntity.ok(Response.ok(service.refreshToken(request, response)));
  }
}
