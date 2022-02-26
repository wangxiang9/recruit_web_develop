package com.exmple.servlet;

import com.exmple.domin.PageBean;
import com.exmple.domin.ResultInfo;
import com.exmple.service.DetailInfoService;
import com.exmple.service.impl.DetailInfoServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/info/*")
public class DetailInfoServlet extends BaseServlet{

    private DetailInfoService detailInfoService=new DetailInfoServiceImpl();

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
        PageBean<ResultInfo> pageBean=detailInfoService.pageQuery(cid,currentPage,pageSize,rnameStr);
        //序列化返回
        responseMsg(pageBean,response);
    }
}
