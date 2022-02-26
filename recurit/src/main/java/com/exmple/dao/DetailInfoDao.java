package com.exmple.dao;

import com.exmple.domin.ResultInfo;

import java.util.List;

public interface DetailInfoDao {
    int queryCount(int cid, String rnameStr);

    List<ResultInfo> queryList(int cid, int start, int pageSize, String rnameStr);
}
