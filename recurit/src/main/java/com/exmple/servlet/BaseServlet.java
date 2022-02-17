package com.exmple.servlet;

import com.exmple.domin.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //完成方法分发
        //1.获取请求路径
        String uri = req.getRequestURI();
        //2.获取方法名称
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);
        //3.获取方法对象Method
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//谁调用就代表谁
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * java对象转json并响应数据方法
     * @param obj
     * @param response
     */
    public void responseMsg(Object obj, HttpServletResponse response){
        //将java对象转成json
        ObjectMapper objectMapper = new ObjectMapper();
        //设置响应content-type
        response.setContentType("application/json;charset=utf-8");
        //响应数据
        try {
            objectMapper.writeValue(response.getOutputStream(),obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查验证码返回错误信息
     * @param session
     * @param check
     * @return
     */
    public ResultInfo checkCheckCode(HttpSession session, String check){
        //获取实际产生的验证码
        String checkCode= (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo resultInfo = new ResultInfo();
        //判断两者是否相同返回信息
        if(checkCode==null||!checkCode.equalsIgnoreCase(check)){
            //存储错误信息
            resultInfo.setFlag(false);
            resultInfo.setMsg("验证码错误");
        }else {
            resultInfo.setFlag(true);
        }
        return resultInfo;
    }
}
