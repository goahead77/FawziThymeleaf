package cn.fawzi.thymeleaf.service.impl;

import cn.fawzi.thymeleaf.service.FawziPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wenqi
 */
@Service
public class FawziPathServiceImpl implements FawziPathService {

    @Autowired
    private HttpServletRequest request;

    @Override
    public String getPath() {
        String serverPrefix = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return serverPrefix;
    }
}
