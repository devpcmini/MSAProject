package pcm.spring.userservice.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pcm.spring.userservice.dto.UserDto;
import pcm.spring.userservice.jpa.UserEntity;
import pcm.spring.userservice.jpa.UserRepository;
import pcm.spring.userservice.vo.ResponseOrder;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if( userEntity == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDto getUserDetailByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null){
            throw new UsernameNotFoundException(email);
        }
        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if(userEntity == null){ //사용자가 존재하지 않는다면
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getEmail(),userEntity.getEncryptedPwd(),
                true, true, true, true,
                new ArrayList<>());
    }
}
