package cn.fawzi.thymeleaf.controller;

import cn.fawzi.thymeleaf.config.TestBase;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author wenqi
 */


public class HeadTest  extends TestBase{


    @Test
    public void testHeader() throws Exception {
        MvcResult mvcResult= mockMvc.perform(get("/safe/head")).andReturn();
        String content=mvcResult.getResponse().getContentAsString();
        System.out.println(content);
    }

}
