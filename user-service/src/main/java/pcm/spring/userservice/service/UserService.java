package pcm.spring.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pcm.spring.userservice.dto.UserDto;
import pcm.spring.userservice.jpa.UserEntity;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();

    UserDto getUserDetailByEmail(String email);
}
