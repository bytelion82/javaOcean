package com.bytelion.jwt.service.impl;

import com.bytelion.jwt.entity.User;
import com.bytelion.jwt.service.IUserService;
import org.springframework.stereotype.Service;


@Service
public class UserService  implements IUserService {
    @Override
    public User findByUsername(User user) {
        return new User("1","byte","byte");
    }

    @Override
    public User findUserById(String userId) {
        return new User("1","byte","byte");
    }
}
