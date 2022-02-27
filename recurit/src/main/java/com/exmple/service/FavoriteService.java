package com.exmple.service;

public interface FavoriteService {
    void addFavorite(int iid, int uid);

    int queryCount(int iid);

    boolean isFavorite(int iid, int uid);
}
