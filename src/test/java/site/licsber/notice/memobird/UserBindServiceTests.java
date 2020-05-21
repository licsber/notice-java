package site.licsber.notice.memobird;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import site.licsber.notice.model.memobird.UserBind;
import site.licsber.notice.service.impl.memobird.UserBindServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserBindServiceTests {

    @Autowired
    private UserBindServiceImpl ser;

    @Autowired
    private MongoTemplate template;

    @BeforeAll
    void init() {
        UserBind userBind = new UserBind();
        userBind.setMemobirdID("123");
        ser.saveUserBind(userBind);

        userBind = new UserBind();
        ser.saveUserBind(userBind);

        userBind = new UserBind();
        userBind.setMemobirdID("aaba7901a443aaaa");
        ser.saveUserBind(userBind);
    }

    @AfterAll
    void after() {
        System.out.println(template.findAll(UserBind.class));
        template.dropCollection(UserBind.class);
    }

    @Test
    void findUserBindByMemoBirdId() {
        Assertions.assertNull(ser.findUserBindByMemoBirdId("23"));
        Assertions.assertNull(ser.findUserBindByMemoBirdId("123"));
        Assertions.assertNotNull(ser.findUserBindByMemoBirdId("aaba7901a443aaaa"));
    }

    @Test
    void deleteUserBindByMemoBirdId() {
        Assertions.assertNotNull(ser.findUserBindByMemoBirdId("aaba7901a443aaaa"));
        ser.deleteUserBindByMemoBirdId("aaba7901a443aaaa");
        Assertions.assertNull(ser.findUserBindByMemoBirdId("aaba7901a443aaaa"));
    }

}
