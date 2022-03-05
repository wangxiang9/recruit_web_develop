package com.exmple.servlet;

import com.exmple.domin.DetailInfo;
import com.exmple.domin.PageBean;
import com.exmple.service.FavoriteService;
import com.exmple.service.UserService;
import com.exmple.service.impl.DetailInfoServiceImpl;
import com.exmple.service.impl.FavoriteServiceImpl;
import com.exmple.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet{

    private DetailInfoServiceImpl detailInfoService=new DetailInfoServiceImpl();

    private FavoriteService favoriteService=new FavoriteServiceImpl();

    private UserService userService=new UserServiceImpl();

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws Exception
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response)throws Exception{
        //获取请求数据
        String currentPageStr = request.getParameter("currentPage");
        String uidStr = request.getParameter("uid");
        //处理数据
        int currentPage=0;
        if(currentPageStr!=null&&currentPageStr.length()>0)currentPage=Integer.parseInt(currentPageStr);
        else currentPage=1;
        int uid=0;//=0时查找所有记录
        if (uidStr!=null&&uidStr.length()>0&&!"null".equals(uidStr)){
            uid=Integer.parseInt(uidStr);
        }
        if (uid==0)return;
        //设置每页显示条数
        int pageSize=10;
        //查询数据
        PageBean<DetailInfo> pageBean=favoriteService.pageQuery(uid,currentPage,pageSize);
        //序列化返回
        responseMsg(pageBean,response);
    }
}
