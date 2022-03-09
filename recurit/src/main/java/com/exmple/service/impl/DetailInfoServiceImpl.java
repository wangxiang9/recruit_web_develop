package com.exmple.service.impl;

import com.exmple.dao.DetailInfoDao;
import com.exmple.dao.FavoriteDao;
import com.exmple.dao.impl.DetailInfoDaoImpl;
import com.exmple.dao.impl.FavoriteDaoImpl;
import com.exmple.domin.DetailInfo;
import com.exmple.domin.PageBean;
import com.exmple.service.DetailInfoService;
import com.exmple.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

public class DetailInfoServiceImpl implements DetailInfoService {

    private DetailInfoDao detailInfoDao=new DetailInfoDaoImpl();

    private FavoriteDao favoriteDao=new FavoriteDaoImpl();

    /**
     * 分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rnameStr
     * @return
     */
    @Override
    public PageBean<DetailInfo> pageQuery(int cid, int currentPage, int pageSize, String rnameStr) {
        //封装pageBean
        PageBean<DetailInfo> pageBean=new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //查询总记录数
        int totalCount=detailInfoDao.queryCount(cid,rnameStr);
        pageBean.setTotalCount(totalCount);
        //查询当前页面显示记录
        int start=(currentPage-1)*pageSize;
        List<DetailInfo> list=detailInfoDao.queryList(cid,start,pageSize,rnameStr);
        pageBean.setList(list);
        //计算总页数
        int totalPage=totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /**
     * 通过iid查询单个数据
     * @param iid
     * @return
     */
    @Override
    public DetailInfo queryOne(int iid) {
        return detailInfoDao.queryOne(iid);
    }

    /**
     * 查询最新十条记录并返回
     * @return
     */
    @Override
    public List<DetailInfo> queryNew() {
        List<DetailInfo>list=null;
        //redis中查找查询
        Jedis jedis=JedisUtil.getJedis();
        String newJson = jedis.get("new");
        if (newJson==null){//jedis中无数据，到数据库中查找并存入jedis
            //查询总记录数
            int count = detailInfoDao.queryCount(0, null);
            list= detailInfoDao.queryNewList(count);
            String jsonString = JSON.toJSONString(list);
            jedis.set("new",jsonString);
            jedis.expire("new",60*60*24);//设置失效时间一天
            return list;
        }else {
            list = JSON.parseArray(newJson, DetailInfo.class);
        }
        return list;
    }

    /**
     * 查询收藏最多10条记录并返回
     * @return
     */
    @Override
    public List<DetailInfo> queryHot() {
        List<DetailInfo>list=null;
        //redis中查找查询
        Jedis jedis=JedisUtil.getJedis();
        String newJson = jedis.get("hot");
        if (newJson==null){//jedis中无数据，到数据库中查找并存入jedis
            //查询总记录数
            int count = detailInfoDao.queryCount(0, null);
            if (count<=10) list=detailInfoDao.queryNewList(count);
            else {
                List<Integer>id_list= favoriteDao.queryHotId(count);
                if (id_list.size()<10){
                    list=detailInfoDao.queryNewList(count);
                }else {
                    list=detailInfoDao.queryHotList(id_list);
                }
            }
            String jsonString = JSON.toJSONString(list);
            jedis.set("hot",jsonString);
            jedis.expire("hot",60*60*24);//设置失效时间一天
            return list;
        }else {
            list = JSON.parseArray(newJson, DetailInfo.class);
        }
        return list;
    }

}
