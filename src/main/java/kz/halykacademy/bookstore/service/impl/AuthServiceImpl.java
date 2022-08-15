package kz.halykacademy.bookstore.service.impl;

import kz.halykacademy.bookstore.config.jwt.JwtAuthenticationResponse;
import kz.halykacademy.bookstore.config.jwt.JwtProvider;
import kz.halykacademy.bookstore.dto.RegistrationLoginDto;
import kz.halykacademy.bookstore.entity.User;
import kz.halykacademy.bookstore.exception.ForbiddenException;
import kz.halykacademy.bookstore.service.api.AuthService;
import kz.halykacademy.bookstore.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private User mapToUser(RegistrationLoginDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userService.save(user);
    }

    @Override
    public JwtAuthenticationResponse registration(RegistrationLoginDto dto) {
        return Optional.ofNullable(dto.getUsername())
                .filter(it -> !userService.existByUsername(dto.getUsername()))
                .map(it -> mapToUser(dto))
                .map(it -> new JwtAuthenticationResponse(jwtProvider.generateToken(it.getUsername())))
                .orElseThrow(()->new EntityNotFoundException("User exist"));
    }

    @Override
    public JwtAuthenticationResponse login(RegistrationLoginDto dto) {
        User user = userService.getByUsername(dto.getUsername());
        return Optional.ofNullable(dto.getPassword())
                .filter(it -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .filter(it -> !user.getIsBlocked())
                .map(it -> new JwtAuthenticationResponse(jwtProvider.generateToken(user.getUsername())))
                .orElseThrow(() -> new ForbiddenException("Password wrong or USER IS BLOCKED"));
    }
}