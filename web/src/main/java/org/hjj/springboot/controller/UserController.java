package org.hjj.springboot.controller;

import org.hjj.springboot.domain.User;
import org.hjj.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hejiajun
 * On 2019/7/25
 */
@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user/save")
    public User save(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        if(userRepository.save(user)) {
            System.out.printf("保存对象：%s，成功\n", user);
        }
        return user;
    }
}
