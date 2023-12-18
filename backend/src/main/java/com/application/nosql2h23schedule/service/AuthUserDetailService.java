package com.application.nosql2h23schedule.service;

import com.application.nosql2h23schedule.domain.User;
import com.application.nosql2h23schedule.repository.UserRepository;
import com.application.nosql2h23schedule.security.UserDomainDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AuthUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AuthUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findUserByEmail(email);

        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Пользователя с таким email не существует");

        return new UserDomainDetails(user.get());
    }
}
