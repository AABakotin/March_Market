package ru.geekbrains.march.market.auth.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.api.JwtRequest;
import ru.geekbrains.march.market.api.JwtResponse;
import ru.geekbrains.march.market.api.UserDto;
import ru.geekbrains.march.market.auth.converters.UserConverter;
import ru.geekbrains.march.market.auth.exeptions.AppError;
import ru.geekbrains.march.market.auth.services.UserService;
import ru.geekbrains.march.market.auth.utils.JwtTokenUtil;

@RestController
@RequiredArgsConstructor
@Tag(name = "Индетификация пользователя", description = "Методы индетификация пользователя")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;


    @Operation(summary = "Индетификация пользователя",
            responses = {
                    @ApiResponse(
                            description = "Пользователь индетифицирован", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Пользователь не индетифицирован", responseCode = "401",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            })
    @PostMapping("/authenticate")
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


    @Operation(summary = "Запрос информации о пользователе",
            responses = {
                    @ApiResponse(description = "Информация получена", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = UserDto.class)))
            })
    @GetMapping("/info")
    public UserDto aboutCurrentUser(@RequestHeader String username) {
        return userConverter.entityToDto(userService.findIdByUsername(username));

    }

}
