package com.microservice.user.controller;

import com.microservice.user.VO.ResponseTemplateVO;
import com.microservice.user.entity.Users;
import com.microservice.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public Users saveUser(@RequestBody Users users){
        log.info("Inside saveUser controller");
        return userService.saveUser(users);
    }
    @GetMapping("/{id}")
    public ResponseTemplateVO getUserAndDepartment(@PathVariable("id") Long userId){
        log.info("Inside getUserAndDepartment controller");
        return userService.getUserAndDepartment(userId);
    }
}
