package com.xm.security;

import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaoming
 * @date 2023-12-17 00:27
 **/
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名去数据库查找用户
        UserEntity user = userService.getByName(username);
        // 校验用户
        if (user == null) {
            log.info("登录账号：{} 不存在！", username);
            throw new UsernameNotFoundException("账号不存在！");
        } else if (user.getStatus() == 1) {
            log.info("登录用户：{} 已锁定！", username);
            throw new LockedException(("账号已锁定！"));
        } else if (user.getIsDelete()) {
            log.info("登录用户：{} 已删除！", username);
            throw new DisabledException("账号已删除！");
        }
        // 角色权限信息
        List<SimpleGrantedAuthority> roles = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        // 返回一个 UserDetails 的实现类，这个是Spring Security提供的类，我们也可以自定义
        return new User(username, user.getPassword(), roles);
    }

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        // 固定使用 BCryptPasswordEncoder 加密密码，如果要整合旧系统不同加密方式，可以使用 DelegatingPasswordEncoder
        return new BCryptPasswordEncoder();
    }
}
