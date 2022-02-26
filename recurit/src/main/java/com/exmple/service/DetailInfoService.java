package com.exmple.service;

import com.exmple.domin.PageBean;
import com.exmple.domin.ResultInfo;

public interface DetailInfoService {
    PageBean<ResultInfo> pageQuery(int cid, int currentPage, int pageSize, String rnameStr);
}
