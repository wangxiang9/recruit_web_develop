package com.exmple.servlet;

import com.exmple.domin.Category;
import com.exmple.service.CategoryService;
import com.exmple.service.impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{

    private CategoryService categoryService=new CategoryServiceImpl();

    /**
     * 获取分类信息数据
     * @param request
     * @param response
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response){
        //查询分类信息序列化为json返回
        List<Category> list=categoryService.findAll();
        responseMsg(list,response);
    }
}
