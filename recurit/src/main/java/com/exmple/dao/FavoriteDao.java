package com.exmple.dao;

public interface FavoriteDao {
    void addFavorite(int iid, int uid);

    int queryCount(int iid);

    int isFavorite(int iid, int uid);
}
