package com.microservice.user.VO;

import com.microservice.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Users users;
    private Department department;

    public void setUsers(Optional<Users> user) {
    }
}
