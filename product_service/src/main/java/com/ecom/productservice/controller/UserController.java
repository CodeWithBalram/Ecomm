package com.ecom.productservice.controller;

import com.ecom.productservice.dto.AuthResponse;
import com.ecom.productservice.dto.RefreshTokenRequest;
import com.ecom.productservice.dto.UserDto;
import com.ecom.productservice.entity.RefreshToken;
import com.ecom.productservice.entity.User;
import com.ecom.productservice.security.JwtUtil;
import com.ecom.productservice.security.RefreshTokenService;
import com.ecom.productservice.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userDetailsService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserDto user) {

        // 1. Authenticate karo
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 2. Roles nikal lo
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        // 3. Access Token generate karo (Short expiry)
        String accessToken = jwtUtil.generateToken(user.getUsername(), roles);

        // 4. Refresh Token generate karo aur database mein save karo (Long expiry)
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        // 5. Dono tokens ko ek Response DTO mein daal kar wapas bhej do
        AuthResponse response = new AuthResponse(accessToken, refreshToken.getToken());

        return ResponseEntity.ok(response);
    }

    // YEH WALA REFRESH ENDPOINT APNE CONTROLLER MEIN ADD KAR LO BHAI!
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    // 1. Role enum ko String list mein convert karo
                    List<String> roles = List.of(user.getRole().name());

                    // 2. getUserName() use karo (kyunki entity mein userName hai)
                    String accessToken = jwtUtil.generateToken(user.getUsername(), roles);

                    // 3. Token Rotation: Purana delete karke naya refresh token dena
                    refreshTokenService.deleteByToken(requestRefreshToken);
                    RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user.getUsername());

                    return ResponseEntity.ok(new AuthResponse(accessToken, newRefreshToken.getToken()));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }

}