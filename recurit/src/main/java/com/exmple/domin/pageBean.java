package com.exmple.domin;

import lombok.Data;

import java.util.List;

@Data
public class pageBean <T>{
    private int totalPage;//总页数
    private int currentPage;//当前页码
    private int totalCount;//总记录数
    private int pageSize;//每页显示条数
    private List<T>list;//每页显示数据集合
}
