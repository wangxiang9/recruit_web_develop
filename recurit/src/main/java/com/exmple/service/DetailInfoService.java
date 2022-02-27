package com.exmple.service;

import com.exmple.domin.DetailInfo;
import com.exmple.domin.PageBean;

public interface DetailInfoService {
    PageBean<DetailInfo> pageQuery(int cid, int currentPage, int pageSize, String rnameStr);

    DetailInfo queryOne(int iid);
}
