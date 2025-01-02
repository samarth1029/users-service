package com.microservice.user.service;

import com.microservice.user.VO.Department;
import com.microservice.user.VO.ResponseTemplateVO;
import com.microservice.user.entity.Users;
import com.microservice.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Users saveUser(Users users) {
        log.info("Inside saveUser service");
        return userRepository.save(users);
    }

    public ResponseTemplateVO getUserAndDepartment(Long userId) {
        log.info("Inside getUserAndDepartment service");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        Optional<Users> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            Department department = restTemplate.getForObject(
                    "http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
                    Department.class
            );

            vo.setUsers(user);
            vo.setDepartment(department);
        } else {
            log.error("User with ID " + userId + " not found");throw new RuntimeException("User not found with ID: " + userId);
        }

        return vo;
    }
}
