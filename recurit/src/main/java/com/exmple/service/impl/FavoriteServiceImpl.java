package com.exmple.service.impl;

import com.exmple.dao.DetailInfoDao;
import com.exmple.dao.FavoriteDao;
import com.exmple.dao.impl.DetailInfoDaoImpl;
import com.exmple.dao.impl.FavoriteDaoImpl;
import com.exmple.domin.DetailInfo;
import com.exmple.domin.Favorite;
import com.exmple.domin.PageBean;
import com.exmple.service.FavoriteService;

import java.util.ArrayList;
import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao=new FavoriteDaoImpl();

    private DetailInfoDao detailInfoDao=new DetailInfoDaoImpl();

    /**
     * 添加一个收藏
     * @param iid
     * @param uid
     */
    @Override
    public void addFavorite(int iid, int uid) {
        favoriteDao.addFavorite(iid,uid);
    }

    /**
     * 根据iid获取收藏次数
     * @param iid
     * @return
     */
    @Override
    public int queryCount(int iid,String name) {
        return favoriteDao.queryCount(iid,name);

    }

    /**
     * 看是否能查到这个数据
     * @param iid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(int iid, int uid) {
        int count=favoriteDao.isFavorite(iid,uid);
        return count==1;
    }

    /**
     * 分页查询用户收藏
     * @param uid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<DetailInfo> pageQuery(int uid, int currentPage, int pageSize) {
        //封装pageBean
        PageBean<DetailInfo> pageBean=new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //查询总记录数和iid列表
        int totalCount=favoriteDao.queryCount(uid,"uid");
        pageBean.setTotalCount(totalCount);
        //查询当前页面显示记录
        int start=(currentPage-1)*pageSize;
        List<Favorite>iids=favoriteDao.queryIds(uid,start,pageSize);
        if (iids==null)return null;
        List<DetailInfo>list=new ArrayList<>();
        for (int i = 0; i < iids.size(); i++) {
            list.add(detailInfoDao.queryOne(iids.get(i).getIid()));
        }
        pageBean.setList(list);
        //计算总页数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
