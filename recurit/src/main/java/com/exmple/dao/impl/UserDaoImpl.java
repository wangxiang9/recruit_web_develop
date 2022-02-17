package com.exmple.dao.impl;

import com.exmple.dao.UserDao;
import com.exmple.domin.User;
import com.exmple.utils.JDBCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    //实例化JdbcTemplate对象
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 通过用户名查询完整用户信息
     * @param userName
     * @return
     */
    @Override
    public User findByUserName(String userName) {
        //定义sql
        String sql="select * from tab_user where username=?";
        User user;
        //执行sql
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),userName);
        } catch (DataAccessException e) {
            user = null;
        }
        return user;
    }

    /**
     * 通过email查询完整用户信息
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        //定义sql
        String sql="select * from tab_user where email=?";
        User user;
        //执行sql
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),email);
        } catch (DataAccessException e) {
            user = null;
        }
        return user;
    }

    /**
     * 保存用户信息
     * @param user
     */
    @Override
    public void saveUser(User user) {
        //定义sql
        String sql="insert into tab_user(username,password,email,code,status)values (?,?,?,?,?)";
        //执行sql
        jdbcTemplate.update(sql,user.getUsername(),user.getPassword(),
                user.getEmail(),user.getCode(),user.getStatus());
    }

    @Override
    public void changeStatusByCode(String code) {
        //定义sql
        String sql="update tab_user set status=? where code=?";
        //执行sql
        jdbcTemplate.update(sql,"Y",code);
    }

    /**
     * 通过用户名或密码查询是否存在
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        //定义sql
        String sql="select * from tab_user where username=? and password=?";
        User user;
        //执行sql
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),username,password);
        } catch (DataAccessException e) {
            user = null;
        }
        return user;
    }
}
