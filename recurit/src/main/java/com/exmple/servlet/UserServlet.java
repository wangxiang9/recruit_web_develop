package com.exmple.servlet;

import com.exmple.domin.ResultInfo;
import com.exmple.domin.User;
import com.exmple.service.UserService;
import com.exmple.service.impl.UserServiceImpl;
import com.exmple.utils.MailUtils;
import com.exmple.utils.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

    private UserService userService=new UserServiceImpl();

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void registered(HttpServletRequest request, HttpServletResponse response) {
        //获取用户输入验证码
        String check=request.getParameter("check");
        //获取session对象
        HttpSession session = request.getSession();
        //检查验证码
        ResultInfo resultInfo = checkCheckCode(session,check);
        if (!resultInfo.isFlag()){
            responseMsg(resultInfo,response);
            return;
        }
        //获取用户提交数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //设置响应状态码和激活码
        user.setStatus("N");
        user.setCode(UuidUtil.getUuid());
        //调用service完成注册
        String msg = userService.registered(user);//正常表示用户名可用
        resultInfo.setMsg(msg);
        resultInfo.setFlag(false);
        if("正常".equals(msg)){
            //发送激活邮件
            resultInfo.setFlag(true);
            try {
                MailUtils.sendMail(user.getEmail(),"<a href='http://localhost/recruit/user/active?code="+user.getCode()+"'>请点击此处激活</a>","激活邮件");
            } catch (Exception e) {
                resultInfo.setFlag(false);
                resultInfo.setMsg("邮箱地址有误");
            }
        }
        responseMsg(resultInfo,response);
    }

    /**
     * 邮件激活
     * @param request
     * @param response
     */
    public void active(HttpServletRequest request, HttpServletResponse response) {
        //获取code
        String code = request.getParameter("code");
        //通过code修改status
        userService.changeStatus(code);
        //重定向到登录界面
        try {
            response.sendRedirect(request.getContextPath()+"/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户登录
     * @param request
     * @param response
     */
    public void login(HttpServletRequest request, HttpServletResponse response) {
        //获取用户输入验证码
        String check=request.getParameter("check");
        //获取session对象
        HttpSession session = request.getSession();
        //检查验证码
        ResultInfo resultInfo = checkCheckCode(session,check);
        if (!resultInfo.isFlag()){
            responseMsg(resultInfo,response);
            return;
        }
        //获取用户提交数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        String msg=userService.login(user);//结果为“正常”即可登录
        if("正常".equals(msg)){//登录成功
            //将user存储在session中
            session.setAttribute("user",user);
            resultInfo.setFlag(true);
        }else{//登录失败
            resultInfo.setFlag(false);
            if("邮箱未激活，请前往邮箱激活".equals(msg)){
                //发送激活邮件
                user=userService.getUser(user.getUsername());//获取完整用户信息
                try {
                    MailUtils.sendMail(user.getEmail(),"<a href='http://localhost/recruit/user/active?code="+user.getCode()+"'>请点击此处激活</a>","激活邮件");
                } catch (Exception e) {
                    resultInfo.setFlag(false);
                    resultInfo.setMsg("邮箱地址有误");
                }
            }
        }
        resultInfo.setMsg(msg);
        responseMsg(resultInfo,response);
    }

    /**
     * 获取用户名响应给客户端
     * @param request
     * @param response
     */
    public void getUsername(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user= (User) session.getAttribute("user");
        responseMsg(user,response);
    }

    /**
     * 退出功能
     * @param request
     * @param response
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁session
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
