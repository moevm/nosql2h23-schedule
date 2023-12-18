package com.application.nosql2h23schedule.service;

import com.application.nosql2h23schedule.domain.User;
import com.application.nosql2h23schedule.dto.UserDto;
import com.application.nosql2h23schedule.repository.UserRepository;
import com.application.nosql2h23schedule.request.AuthRequest;
import com.application.nosql2h23schedule.request.RegisterRequest;
import com.application.nosql2h23schedule.security.JwtUtil;
import com.application.nosql2h23schedule.security.UserDomainDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final AuthUserDetailService authUserDetailService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(AuthUserDetailService authUserDetailService, UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authUserDetailService = authUserDetailService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserDto authenticateUser(AuthRequest authRequest) {

        UserDomainDetails userDetails = (UserDomainDetails) authUserDetailService.loadUserByUsername(authRequest.getEmail());

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(),
                        authRequest.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Неверный пароль");
        }

        String token = jwtUtil.generateToken(authRequest.getEmail());

        User user = new User();
        user.setEmail(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setFullName(userDetails.getUser().getFullName());
        user.setRole(userDetails.getUser().getRole());

        return new UserDto(token, user.getEmail(), user.getPassword(), user.getFullName(), user.getRole());
    }

    @Transactional
    public UserDto registerUser(RegisterRequest registerRequest) {

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFullName(registerRequest.getFullName());
        user.setRole("ROLE_ADMIN");

        if (userRepository.findUserByEmail(user.getEmail()).isPresent())
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Пользователь с таким email уже существует.");

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail());

        return new UserDto(token, user.getEmail(), user.getPassword(), user.getFullName(), user.getRole());
    }
}
