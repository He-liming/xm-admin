package com.xm;

import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class ApplicationTests {

    @Resource
    UserRepository userRepository;

    @Test
    void saveUserTest() {
        UserEntity user = new UserEntity();
        user.setName("张三");
        user.setNickname("法外狂徒");
        user.setAge(18);
        user.setPassword("666");
        // 保存用户并返回
        UserEntity result = userRepository.save(user);
        log.info("用户添加成功：{}", result);
    }

    @Test
    void contextLoads() {
    }

}
