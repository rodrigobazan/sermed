package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import ar.com.koodi.sermedboundaries.SermedServices.SecurityConfig.CustomUserDetailsService;
import ar.com.koodi.sermedboundaries.SermedServices.SecurityConfig.JwtTokenProvider;
import ar.com.koodi.sermedboundaries.SermedServices.Login.JwtAuthenticationResponse;
import ar.com.koodi.sermedboundaries.SermedServices.Login.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/sermed/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLogin(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(customUserDetailsService.loadUserByUsername(loginRequest.getLogin()));
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
