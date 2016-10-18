package cn.fawzi.thymeleaf.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author wenqi
 */
public interface QRCodeService {

    StringBuilder decoder(MultipartFile file) throws IOException;

}
