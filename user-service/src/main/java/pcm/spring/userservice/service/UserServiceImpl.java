package pcm.spring.userservice.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pcm.spring.userservice.dto.UserDto;
import pcm.spring.userservice.jpa.UserEntity;
import pcm.spring.userservice.jpa.UserRepository;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    public UserServiceImpl (UserRepository userRepository,
                            BCryptPasswordEncoder bCryptEncoder){
        this.userRepository = userRepository;
        this.bCryptEncoder = bCryptEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(bCryptEncoder.encode(userDto.getPwd())); //임시값
        userRepository.save(userEntity);

        UserDto rtnUserDto = mapper.map(userEntity,UserDto.class);

        return rtnUserDto;
    }
}
