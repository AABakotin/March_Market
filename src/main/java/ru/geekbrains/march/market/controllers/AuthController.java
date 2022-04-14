package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.dtos.JwtRequest;
import ru.geekbrains.march.market.dtos.JwtResponse;
import ru.geekbrains.march.market.dtos.UserDto;
import ru.geekbrains.march.market.exceptions.AppError;
import ru.geekbrains.march.market.services.UserService;
import ru.geekbrains.march.market.utils.JwtTokenUtil;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError("CHECK_TOKEN_ERROR", "Некорректный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    // Передаю имя и эмайл. В постмане работает отлично, фронт нет.
    // На фронте необходимо как то сохранять токен и его постоянно передавать.
    // Т.к. токен не запоминается, после входа нет корзины...

    @GetMapping("/check_email")
    public UserDto infoMailRequest(Principal principal) {

        UserDto userDto = new UserDto();
        if (principal == null) {
            userDto.setName("None");
            userDto.setEmail("None@none");
            return userDto;
        }
        userDto.setName(principal.getName());
        userDto.setEmail(userService.findByUsername(userDto.getName()).orElseThrow().getEmail());
        return userDto;
    }
}
