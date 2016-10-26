package cn.fawzi.thymeleaf.service;

import cn.fawzi.thymeleaf.entity.Emotion;

import java.io.IOException;
import java.util.List;

/**
 * @author wenqi
 */
public interface EmotionService {

    List<Emotion> getEmotions() throws IOException;

}
