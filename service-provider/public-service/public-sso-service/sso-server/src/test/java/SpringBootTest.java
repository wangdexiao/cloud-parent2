import com.study.ssoserver.SsoApplication;
import com.study.ssoserver.mapper.UserMapper;
import com.study.ssoserver.service.MyUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.security.RunAs;

@Slf4j
@RunWith(SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes = {SsoApplication.class})
public class SpringBootTest {


    @Autowired
    private UserMapper userService;

    @Test
    public void testAddUser() {
        UserDetails userDetails = userService.loadUserByUsername("admin");
        log.info("用户信息user:" + userDetails.toString());
    }
}
