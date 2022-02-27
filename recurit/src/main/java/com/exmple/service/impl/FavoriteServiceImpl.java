package com.exmple.service.impl;

import com.exmple.dao.FavoriteDao;
import com.exmple.dao.impl.FavoriteDaoImpl;
import com.exmple.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao=new FavoriteDaoImpl();

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
    public int queryCount(int iid) {
        return favoriteDao.queryCount(iid);

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
}
