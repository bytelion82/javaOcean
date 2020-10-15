package com.bytelion.jwt.service;

import com.bytelion.jwt.entity.User;

public interface IUserService {

    public User findByUsername(User user);

    public User findUserById(String userId);
}
