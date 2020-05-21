package site.licsber.notice.memobird;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.licsber.notice.model.memobird.UserBind;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SetUserBindServiceTests {

    @Autowired
    private MongoTemplate template;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeAll
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterAll
    void after() {
        System.out.println(template.findAll(UserBind.class));
        template.dropCollection(UserBind.class);
    }

    @Test
    void setUserBind() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/bind")
                .param("memoBirdId", "")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(""))
                .andDo(MockMvcResultHandlers.print());

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/bind")
                .param("memoBirdId", "aaba7901a443aaaa")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(""))
                .andDo(MockMvcResultHandlers.print());
    }

}
