package com.jiaotd.core.controller;

import com.jiaotd.core.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：TODO
 * Date:     2020/3/22
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/core")
public class CoreController {

    @GetMapping("/user")
    public User getUser(User user) {
        return user;
    }
}
