package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.entity.Greeting;
import cn.fawzi.thymeleaf.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wenqi
 */

@Controller
public class MessageController {

    @MessageMapping("/hello")
    @SendTo("/response/greetings")
    public Greeting greeting(Message message) throws Exception {

        String content=message.getContent();

        String baidu="<a href='http://www.baidu.com/s?wd="+content+"'>点击链接</a>";

        return new Greeting(baidu);
    }

}
