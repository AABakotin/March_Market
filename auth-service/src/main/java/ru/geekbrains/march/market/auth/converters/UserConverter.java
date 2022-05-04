package ru.geekbrains.march.market.auth.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.api.UserDto;
import ru.geekbrains.march.market.auth.entities.User;


@Component
public class UserConverter {


    public UserDto entityToDto(User u) {

        UserDto userDto = new UserDto();
        userDto.setName(u.getUsername());
        userDto.setEmail(u.getEmail());

        return userDto;
    }
}
