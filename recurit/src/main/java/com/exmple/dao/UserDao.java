package com.exmple.dao;

import com.exmple.domin.User;

public interface UserDao {
    User findByUserName(String userName);

    User findByEmail(String email);

    void saveUser(User user);

    void changeStatusByCode(String code);

    User findUserByUsernameAndPassword(String username, String password);

    String findStatusByUsername(String username);
}
