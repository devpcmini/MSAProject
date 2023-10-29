package pcm.spring.userservice.controller;

import com.netflix.discovery.converters.Auto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pcm.spring.userservice.dto.UserDto;
import pcm.spring.userservice.service.UserService;
import pcm.spring.userservice.vo.Greeting;
import pcm.spring.userservice.vo.RequestUser;
import pcm.spring.userservice.vo.ResponseUser;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private Environment env;
    private UserService userService;

    @Autowired
    private Greeting greeting;

    @Autowired
    public UserController(Environment env, UserService userService){
        this.env = env;
        this.userService = userService;
    }

    //userService client 포트번호 return
    @GetMapping("/healthCheck")
    public String healthcheck(){
        return String.format("Port %s",env.getProperty("local.server.port"));
    }

    @GetMapping("/test")
    public String test(){
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(requestUser, UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class); //userDto를 ResponseUser 로 타입 바꾸기

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser); //201코드 반환
    }

}
