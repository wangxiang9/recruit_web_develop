package com.exmple.dao;

import com.exmple.domin.Favorite;

import java.util.List;

public interface FavoriteDao {
    void addFavorite(int iid, int uid);

    int queryCount(int iid, String name);

    int isFavorite(int iid, int uid);

    List<Favorite> queryIds(int uid, int start, int pageSize);

    List<Integer> queryHotId(int count);
}
