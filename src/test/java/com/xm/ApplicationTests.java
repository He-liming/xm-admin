package com.xm;

import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.repository.UserRepository;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@SpringBootTest
class ApplicationTests {

    @Resource
    UserRepository userRepository;

    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void saveUserTest() {
        UserEntity user = new UserEntity();
        user.setName("张三");
        user.setPassword("666");
        // 保存用户并返回
        UserEntity result = userRepository.save(user);
        log.info("用户添加成功：{}", result);
    }

    @Test
    void testRedis() {
        UserEntity user = new UserEntity();
        user.setName("张三");
        user.setPassword("666");
        redisTemplate.opsForValue().set("user", user);
    }

    @Test
    void contextLoads() {
    }

}
