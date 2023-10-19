package com.example.securityjwtpractice.auth;

import com.example.securityjwtpractice.config.JwtService;
import com.example.securityjwtpractice.user.Role;
import com.example.securityjwtpractice.user.User;
import com.example.securityjwtpractice.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;  //password have to be encoded when saved
    private final JwtService jwtService;

    //allow to create user and save it to db
    //return generated token
    public AuthenticationResponse register(RegisterRequest request) {

        //create User object out of RegisterRequest
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        //save user into DB
        repository.save(user);

        //return AuthenticationResponse
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return null;
    }
}
