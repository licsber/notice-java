package site.licsber.notice.memobird;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import site.licsber.notice.model.memobird.UserBind;
import site.licsber.notice.repository.memobird.UserBindRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserBindServiceTests {

    @Autowired
    private UserBindRepository userBindRepository;

    @Autowired
    private MongoTemplate template;

    final String validString = "aaba7901a443aaaa";

    @BeforeAll
    void init() {
        UserBind userBind = new UserBind("123");
        userBindRepository.save(userBind);

        userBind = new UserBind("");
        userBindRepository.save(userBind);

        userBind = new UserBind(validString);
        userBindRepository.save(userBind);
    }

    @AfterAll
    void after() {
        System.out.println(template.findAll(UserBind.class));
        template.dropCollection(UserBind.class);
    }

    @Test
    void findUserBindByMemoBirdId() {
        Assertions.assertNull(userBindRepository.findByMemoBirdID("23"));
        Assertions.assertNotNull(userBindRepository.findByMemoBirdID("123"));
        Assertions.assertNotNull(userBindRepository.findByMemoBirdID(validString));
    }

    @Test
    void deleteUserBindByMemoBirdId() {
        Assertions.assertNotNull(userBindRepository.findByMemoBirdID(validString));
        userBindRepository.deleteByMemoBirdID(validString);
        Assertions.assertNull(userBindRepository.findByMemoBirdID(validString));
    }

}
