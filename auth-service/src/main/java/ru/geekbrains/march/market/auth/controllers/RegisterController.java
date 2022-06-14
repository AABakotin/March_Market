package ru.geekbrains.march.market.auth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.api.RegisterUserDto;
import ru.geekbrains.march.market.auth.converters.RegisterUserConverter;
import ru.geekbrains.march.market.auth.services.UserService;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final UserService userService;
    private final RegisterUserConverter registerUserConverter;


    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registrationNewUser(@RequestBody RegisterUserDto registerUserDto) {
        if (userService.findByUsername(registerUserDto.getUsername()).isPresent()) {
            throw new IllegalStateException("Пользователь с таким именем уже существует");
        }
        userService.saveNewUser(registerUserConverter.dtoToUserEntity(registerUserDto));
    }
}