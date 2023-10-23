package csc394.artisanshop.datamapper;

import csc394.artisanshop.dto.UserDto;
import csc394.artisanshop.entities.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        if (user.getOrders() != null) {
            userDto.setOrders(user.getOrders().stream().map(OrderMapper::toOrderDto).collect(Collectors.toList()));
        }


        return userDto;
    }

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        if (userDto.getOrders() != null) {
            user.setOrders(userDto.getOrders().stream().map(OrderMapper::toOrder).collect(Collectors.toList()));
        }

        return user;
    }
}
