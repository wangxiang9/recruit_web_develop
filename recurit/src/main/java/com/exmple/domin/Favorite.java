package com.exmple.domin;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Data
public class Favorite {
    private Integer iid;
    private Date creatDate;
    private Integer uid;
}
