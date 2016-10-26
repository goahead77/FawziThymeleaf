package cn.fawzi.thymeleaf.service.impl;

import cn.fawzi.thymeleaf.entity.Emotion;
import cn.fawzi.thymeleaf.service.EmotionService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wenqi
 */

@Service
public class EmotionServiceImpl implements EmotionService {
    @Override
    public List<Emotion> getEmotions() throws IOException {
        InputStream is=EmotionServiceImpl.class.getResourceAsStream("/emotions.json");
        ObjectMapper objectMapper=new ObjectMapper();
        JavaType javaType=objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Emotion.class);
        return objectMapper.readValue(is,javaType);
    }
}
