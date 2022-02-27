package com.exmple.dao;

import com.exmple.domin.DetailInfo;

import java.util.List;

public interface DetailInfoDao {

    public int queryCount(int cid, String rnameStr);

    public List<DetailInfo> queryList(int cid, int start, int pageSize, String rnameStr);

    DetailInfo queryOne(int iid);
}
