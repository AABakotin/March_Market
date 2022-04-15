package ru.geekbrains.march.market.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.UserDto;
import ru.geekbrains.march.market.entities.User;


@Component
public class UserConverter {


    public UserDto entityToDto(User u) {

        UserDto userDto = new UserDto();
        userDto.setName(u.getUsername());
        userDto.setEmail(u.getEmail());

        return userDto;
    }
}
