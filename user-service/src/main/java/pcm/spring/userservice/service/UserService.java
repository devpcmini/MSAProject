package pcm.spring.userservice.service;

import pcm.spring.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
