package com.exmple.dao.impl;

import com.exmple.dao.CategoryDao;
import com.exmple.domin.Category;
import com.exmple.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询所有分类信息
     * @return
     */
    @Override
    public List<Category> findAll() {
        String sql="select * from tab_category";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Category.class));
    }
}
