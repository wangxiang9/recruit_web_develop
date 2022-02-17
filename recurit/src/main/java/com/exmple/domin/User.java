package com.exmple.domin;

import lombok.Data;

@Data
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String email;
    private String status;
    private String code;
}
