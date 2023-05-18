package com.accolite.alertMessenger.controller;

import com.accolite.alertMessenger.model.JwtRequest;
import com.accolite.alertMessenger.model.JwtResponse;
import com.accolite.alertMessenger.model.UserDetail;
import com.accolite.alertMessenger.service.implementation.JwtUserDetailsService;
import com.accolite.alertMessenger.utility.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    BCryptPasswordEncoder bCryptPasswordEncoder =
            new BCryptPasswordEncoder();

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception{
//        authenticationRequest.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(token.toString());

    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDetail user) throws Exception{
       return new ResponseEntity<UserDetail>(jwtUserDetailsService.save(user), HttpStatus.CREATED);
    }

    private void authenticate(String username, String password) throws Exception{
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new Exception("USER_DISABLED", e);
        }catch(BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
