package com.exmple.servlet;

import com.exmple.domin.DetailInfo;
import com.exmple.domin.PageBean;
import com.exmple.domin.ResultInfo;
import com.exmple.domin.User;
import com.exmple.service.DetailInfoService;
import com.exmple.service.FavoriteService;
import com.exmple.service.UserService;
import com.exmple.service.impl.DetailInfoServiceImpl;
import com.exmple.service.impl.FavoriteServiceImpl;
import com.exmple.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/info/*")
public class DetailInfoServlet extends BaseServlet{

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
        String cidStr = request.getParameter("cid");
        String rnameStr = request.getParameter("rname");
        //设置编码格式
        rnameStr=new String(rnameStr.getBytes("iso-8859-1"),"utf-8");
        //处理数据
        int currentPage=0;
        if(currentPageStr!=null&&currentPageStr.length()>0)currentPage=Integer.parseInt(currentPageStr);
        else currentPage=1;
        int cid=0;//=0时查找所有记录
        if (cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        //设置每页显示条数
        int pageSize=10;
        //查询数据
        PageBean<DetailInfo> pageBean=detailInfoService.pageQuery(cid,currentPage,pageSize,rnameStr);
        //序列化返回
        responseMsg(pageBean,response);
    }

    /**
     * 添加一个收藏
     * @param request
     * @param response
     */
    public void addFavorite(HttpServletRequest request,HttpServletResponse response){
        //获取iid
        String iidStr = request.getParameter("iid");
        int iid = Integer.parseInt(iidStr);
        //获取用户uid
        User user =(User) request.getSession().getAttribute("user");
        int uid = userService.getUser(user.getUsername()).getUid();
        //添加收藏
        favoriteService.addFavorite(iid,uid);
    }

    /**
     * 根据iid获取收藏次数
     * @param request
     * @param response
     */
    public void favoriteCount(HttpServletRequest request,HttpServletResponse response){
        //获取iid
        String iidStr = request.getParameter("iid");
        int iid = Integer.parseInt(iidStr);
        //获取收藏次数
        int count=favoriteService.queryCount(iid,"iid");
        responseMsg(count,response);
    }

    /**
     * 当前用户是否收藏
     * @param request
     * @param response
     */
    public void isFavorite(HttpServletRequest request,HttpServletResponse response){
        //获取iid
        String iidStr = request.getParameter("iid");
        //数据转换
        int iid = Integer.parseInt(iidStr);
        //获取用户uid
        User user =(User) request.getSession().getAttribute("user");
        int uid = userService.getUser(user.getUsername()).getUid();
        //查询是否被收藏
        boolean flag=favoriteService.isFavorite(iid,uid);
        responseMsg(flag,response);
    }
    /**
     * 根据iid查询单个记录
     * @param request
     * @param response
     * @throws Exception
     */
    public void queryOne(HttpServletRequest request,HttpServletResponse response)throws Exception{
        //获取iid
        String iidStr = request.getParameter("iid");
        //数据转换
        int iid = Integer.parseInt(iidStr);
        //查询数据
        DetailInfo detailInfo=detailInfoService.queryOne(iid);
        //序列化返回
        responseMsg(detailInfo,response);
    }
}
