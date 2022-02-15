package com.exmple.web.servlet;

import com.exmple.domin.ResultInfo;
import com.exmple.service.impl.UserServletImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    @Autowired
    private UserServletImpl userServlet;

    /**
     * 用户注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void registered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("1111");
        //获取用户输入验证码
        String check=request.getParameter("check");
        //获取session对象
        HttpSession session = request.getSession();
        //获取实际产生的验证码
        String checkCode= (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        //判断两者是否相同返回信息
        if(checkCode==null||!checkCode.equalsIgnoreCase(check)){
            //存储错误信息
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setMsg("验证码错误");
            responseMsg(resultInfo,response);
            return;
        }
    }
}
