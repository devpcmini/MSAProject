package pcm.spring.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor //argument를 다 가지고 있는 생성자 하나 만들어주겠다
@NoArgsConstructor //default 생성자를 만들어준다
public class Greeting {
    @Value("${greeting.message}")
    private String message;
}
