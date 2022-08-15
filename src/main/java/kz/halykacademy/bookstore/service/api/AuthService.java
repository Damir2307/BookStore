package kz.halykacademy.bookstore.service.api;

import kz.halykacademy.bookstore.config.jwt.JwtAuthenticationResponse;
import kz.halykacademy.bookstore.dto.RegistrationLoginDto;

public interface AuthService {
    JwtAuthenticationResponse registration(RegistrationLoginDto registrationDto);
    JwtAuthenticationResponse login(RegistrationLoginDto loginDto);
}
