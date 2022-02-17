package com.exmple.service.impl;

import com.exmple.common.constant.ErrorMsgEnum;
import com.exmple.dao.impl.UserDaoImpl;
import com.exmple.domin.User;
import com.exmple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    private UserDaoImpl userDao =new UserDaoImpl();

    /**
     * 判断注册信息是否可用
     * @param user
     * @return
     */
    @Override
    public String registered(User user) {
        Integer code =1;//code默认是1即正常
        try {
            //判断用户名是否存在修改code
            User findByName=userDao.findByUserName(user.getUsername());
            if (findByName!=null)code=2;
            //判断邮箱是否存在修改code
            User findByEmail=userDao.findByEmail(user.getEmail());
            if (findByEmail!=null)code=3;
        } catch (Exception e) {
            code=4;
        }
        //保存此用户
        if (code.equals(1)) userDao.saveUser(user);
        return ErrorMsgEnum.getValueByCode(code);
    }

    /**
     * 通过code修改status
     * @param code
     */
    @Override
    public void changeStatus(String code) {
        //通过code修改status
        userDao.changeStatusByCode(code);
    }

    /**
     * 返回用户登录提示信息
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        Integer code =1;//code默认是1即正常
        //查询用户名或密码是否正确修改code
        User userByU_P=userDao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(userByU_P==null)code=4;
        else{//查询是否激活修改code
            String status=userDao.findStatusByUsername(user.getUsername());
            if("N".equals(status))code=5;
        }
        return ErrorMsgEnum.getValueByCode(code);
    }
}
