package com.exmple.dao;

import com.exmple.domin.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll();
}
