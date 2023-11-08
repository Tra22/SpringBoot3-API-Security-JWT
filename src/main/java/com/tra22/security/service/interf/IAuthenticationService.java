package com.tra22.security.service.interf;

import com.tra22.security.payload.request.AuthenticationRequest;
import com.tra22.security.payload.request.RegisterRequest;
import com.tra22.security.payload.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IAuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response);
}
