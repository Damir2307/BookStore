package kz.halykacademy.bookstore.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.halykacademy.bookstore.config.jwt.JwtAuthenticationResponse;
import kz.halykacademy.bookstore.dto.RegistrationLoginDto;
import kz.halykacademy.bookstore.service.api.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(description="API для авторизаций", tags = "API для авторизаций")
@Controller
@RequestMapping("/v1/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Регистрация пользователя", notes = "Регистрация пользователя")
    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> registerUser(@RequestBody @Valid RegistrationLoginDto registrationRequest) {
        return ResponseEntity.ok(authService.registration(registrationRequest));
    }

    @ApiOperation(value = "Вход пользователя", notes = "Вход пользователя")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody @Valid RegistrationLoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}