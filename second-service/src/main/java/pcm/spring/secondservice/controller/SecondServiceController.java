package pcm.spring.secondservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/second-service")
public class SecondServiceController {

	Environment env;

	public SecondServiceController(Environment env){
		this.env = env;
	}

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to the Second service.";
	}
	
	@GetMapping("/message")
	public String message(@RequestHeader("second-request") String header) {
		log.info("header => {}",header);
		return "Hello World in Second Service.";
	}

	@GetMapping("/check")
	public String check(HttpServletRequest request){
		log.info("check server port => {}", request.getServerPort());
		return String.format("port = %s",env.getProperty("local.server.port"));
	}
}
