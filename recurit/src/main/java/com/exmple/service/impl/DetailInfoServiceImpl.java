package com.exmple.service.impl;

import com.exmple.dao.DetailInfoDao;
import com.exmple.dao.impl.DetailInfoDaoImpl;
import com.exmple.domin.PageBean;
import com.exmple.domin.ResultInfo;
import com.exmple.service.DetailInfoService;

import java.util.List;

public class DetailInfoServiceImpl implements DetailInfoService {

    private DetailInfoDao detailInfoDao=new DetailInfoDaoImpl();

    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rnameStr
     * @return
     */
    @Override
    public PageBean<ResultInfo> pageQuery(int cid, int currentPage, int pageSize, String rnameStr) {
        //封装pageBean
        PageBean<ResultInfo> pageBean=new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //查询总记录数
        int totalCount=detailInfoDao.queryCount(cid,rnameStr);
        pageBean.setTotalCount(totalCount);
        //查询当前页面显示记录
        int start=(currentPage-1)*pageSize;
        List<ResultInfo> list=detailInfoDao.queryList(cid,start,pageSize,rnameStr);
        pageBean.setList(list);
        //计算总页数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
