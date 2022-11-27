package io.github.artsiomshshshsk.mydictionary.controller;


import io.github.artsiomshshshsk.mydictionary.security.jwt.JwtUtils;
import io.github.artsiomshshshsk.mydictionary.model.User;
import io.github.artsiomshshshsk.mydictionary.security.payload.request.LoginRequest;
import io.github.artsiomshshshsk.mydictionary.security.payload.request.RegisterRequest;
import io.github.artsiomshshshsk.mydictionary.security.payload.response.JwtResponse;
import io.github.artsiomshshshsk.mydictionary.security.payload.response.MessageResponse;
import io.github.artsiomshshshsk.mydictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserService userService,
            PasswordEncoder encoder,
            JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User user = userService.findByEmail(userDetails.getUsername()).get();

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) throws MessagingException, UnsupportedEncodingException {
        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(encoder.encode(registerRequest.getPassword()))
                .build();
        String url = (((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()).getRequestURL().toString();
        String baseUrl = url.split("/api")[0];
        userService.sendVerificationEmail(userService.register(user),baseUrl);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestParam String verificationToken){
        boolean verified = userService.verify(verificationToken);
        if(verified){
            return ResponseEntity.ok(new MessageResponse("Email confirmed successfully!"));
        }else{
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email confiramtion error"));
        }
    }

}