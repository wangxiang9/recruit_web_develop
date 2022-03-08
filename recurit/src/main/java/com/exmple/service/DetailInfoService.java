package com.exmple.service;

import com.exmple.domin.DetailInfo;
import com.exmple.domin.PageBean;

import java.util.List;

public interface DetailInfoService {
    PageBean<DetailInfo> pageQuery(int cid, int currentPage, int pageSize, String rnameStr);

    DetailInfo queryOne(int iid);

    List<DetailInfo> queryNew();

    List<DetailInfo> queryHot();
}
