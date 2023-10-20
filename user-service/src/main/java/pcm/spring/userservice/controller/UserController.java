package pcm.spring.userservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
public class UserController {

//    private Environment env;

//    @Value("${greeting.message}")
//    private String message;

//    public UserController(Environment env){
//        this.env = env;
//    }

    @GetMapping("/healthCheck")
    public String healthcheck(){
        return "ok";
    }

    @GetMapping("/test")
    public String test(){
//        return env.getProperty("greeting.message");
//        return message;
        return "test";
    }

}
