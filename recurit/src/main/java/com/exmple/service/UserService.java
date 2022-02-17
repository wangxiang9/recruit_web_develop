package com.exmple.service;

import com.exmple.domin.User;

public interface UserService {
    String registered(User user);

    void changeStatus(String code);

    String login(User user);

    User getUser(String username);
}
