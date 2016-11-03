package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Greeting;
import cn.fawzi.thymeleaf.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author wenqi
 */

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(Message message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Message is:, " + message + "!");
    }

}
