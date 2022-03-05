package com.exmple.service;

import com.exmple.domin.DetailInfo;
import com.exmple.domin.PageBean;

public interface FavoriteService {
    void addFavorite(int iid, int uid);

    int queryCount(int iid,String name);

    boolean isFavorite(int iid, int uid);

    PageBean<DetailInfo> pageQuery(int uid, int currentPage, int pageSize);
}
