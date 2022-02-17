package com.exmple.servlet;

import com.exmple.domin.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param resultInfo
     * @param response
     */
    public void responseMsg(ResultInfo resultInfo, HttpServletResponse response){
        //将java对象转成json
        ObjectMapper objectMapper = new ObjectMapper();
        //设置响应content-type
        response.setContentType("application/json;charset=utf-8");
        //响应数据
        try {
            objectMapper.writeValue(response.getOutputStream(),resultInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
