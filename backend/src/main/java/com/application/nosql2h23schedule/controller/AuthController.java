package com.application.nosql2h23schedule.controller;

import com.application.nosql2h23schedule.dto.UserDto;
import com.application.nosql2h23schedule.repository.UserRepository;
import com.application.nosql2h23schedule.request.AuthRequest;
import com.application.nosql2h23schedule.request.RegisterRequest;
import com.application.nosql2h23schedule.security.UserDomainDetails;
import com.application.nosql2h23schedule.service.AuthService;
import com.application.nosql2h23schedule.service.AuthUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/schedule/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.authenticateUser(authRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerUser(registerRequest));
    }

    @GetMapping("/smth")
    public ResponseEntity<String> smth(){
        return ResponseEntity.ok("OKEY");
    }
}
