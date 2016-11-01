package cn.fawzi.thymeleaf.config;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author wenqi
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class TestBase {

    @Autowired
    private WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    @Before
    public void buildMockMvc(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
