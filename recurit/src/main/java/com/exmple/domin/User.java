package com.exmple.domin;

import lombok.Data;

@Data
public class User {
    private Integer uid;
    private String userName;
    private String passWord;
    private String email;
    private String status;
    private String code;
}
