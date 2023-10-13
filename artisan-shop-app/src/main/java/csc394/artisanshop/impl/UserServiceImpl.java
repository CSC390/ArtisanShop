package csc394.artisanshop.services.impl;

import csc394.artisanshop.entities.User;
import csc394.artisanshop.datamapper.UserMapper;
import csc394.artisanshop.dto.UserDto;
import csc394.artisanshop.repositories.UserDtoRepository;
import csc394.artisanshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDtoRepository userDtoRepository;

    @Autowired
    public UserServiceImpl(UserDtoRepository userDtoRepository) {
        this.userDtoRepository = userDtoRepository;
    }

    @Override
    @Transactional
    public User register(User user) {
        UserDto userDto = UserMapper.toUserDto(user);
        UserDto savedUserDto = userDtoRepository.save(userDto);
        return UserMapper.toUser(savedUserDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUserName(String username) {
        Optional<UserDto> userDtoOptional = userDtoRepository.findByUsername(username);
        return userDtoOptional.map(UserMapper::toUser);
    }

    @Override
    @Transactional
    public User updateProfile(User user) {
        if(user.getUserId() == null) {
            throw new IllegalArgumentException("User ID cannot be null during update");
        }

        UserDto userDto = UserMapper.toUserDto(user);
        UserDto updatedUserDto = userDtoRepository.save(userDto);
        return UserMapper.toUser(updatedUserDto);
    }

    // Other methods can follow a similar pattern...
}
