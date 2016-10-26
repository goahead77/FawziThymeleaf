package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.service.EmotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @author wenqi
 */

@Controller
public class EmotionController {

    @Autowired
    private EmotionService emotionService;

    @RequestMapping("/emotions")
    public String showEmotions(Model model) throws IOException {
        model.addAttribute("emotions",emotionService.getEmotions());
        return "emotions";
    }
}
