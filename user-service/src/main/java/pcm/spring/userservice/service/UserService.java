package pcm.spring.userservice.service;

import pcm.spring.userservice.dto.UserDto;
import pcm.spring.userservice.jpa.UserEntity;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
}
