package test;

import com.bjpowernode.pojo.User;
import com.bjpowernode.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 启动Spring容器
@ContextConfiguration(locations = {"classpath:applicationContext_mapper.xml","classpath:applicationContext_service.xml"})
public class MyTest {
    @Autowired
    UserService userService;

    @Test
    public void testSelectUserPage(){
        List<User> users = userService.selectUserPage(null, null, 0);
        users.forEach(user -> System.out.println(user));
    }
}
