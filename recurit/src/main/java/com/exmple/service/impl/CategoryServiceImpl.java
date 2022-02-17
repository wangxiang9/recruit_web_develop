package com.exmple.service.impl;

import com.exmple.dao.CategoryDao;
import com.exmple.dao.impl.CategoryDaoImpl;
import com.exmple.domin.Category;
import com.exmple.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao=new CategoryDaoImpl();

    /**
     * 查询分类信息
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
