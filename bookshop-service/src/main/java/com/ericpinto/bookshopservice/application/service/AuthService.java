package com.ericpinto.bookshopservice.application.service;

import com.ericpinto.bookshopservice.application.dto.AuthRequest;
import com.ericpinto.bookshopservice.application.dto.AuthResponse;
import com.ericpinto.bookshopservice.application.dto.RegisterRequest;
import com.ericpinto.bookshopservice.domain.model.User;
import com.ericpinto.bookshopservice.domain.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }

        User user = User.create(registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()));

        userRepository.save(user);

        return "User registered successfully";
    }

    public AuthResponse login(AuthRequest authRequest){
        Optional<User> user = userRepository.findByUsername(authRequest.username());
        if (user.isEmpty() || !passwordEncoder.matches(authRequest.password(),user.get().getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String token = jwtService.generateToken(authRequest.username());
        return new AuthResponse(token);
    }
}
