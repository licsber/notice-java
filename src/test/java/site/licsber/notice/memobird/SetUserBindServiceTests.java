package site.licsber.notice.memobird;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import site.licsber.notice.repository.memobird.UserBindRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SetUserBindServiceTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserBindRepository userBindRepository;

    private MockMvc mockMvc;

    @BeforeAll
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterAll
    void after() {
        userBindRepository.deleteAll();
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
                .param("memoBirdId", "fb93bfff504c020a")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(""))
                .andDo(MockMvcResultHandlers.print());
    }

}
