package com.exmple.dao.impl;

import com.exmple.dao.DetailInfoDao;
import com.exmple.domin.ResultInfo;
import com.exmple.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class DetailInfoDaoImpl implements DetailInfoDao {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据cid查询总记录数
     * @param cid
     * @param rnameStr
     * @return
     */
    @Override
    public int queryCount(int cid, String rnameStr) {
        //定义sql模板
        String sql="select count(*) from tab_info_list where 1=1";
        List para=new ArrayList();//条件们
        StringBuilder sb = sqlTemplate(cid, rnameStr, para, sql);
        sql=sb.toString();
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Integer.class),para.toArray());
    }

    /**
     * 分页查询数据列表
     * @param cid
     * @param start
     * @param pageSize
     * @param rnameStr
     * @return
     */
    @Override
    public List<ResultInfo> queryList(int cid, int start, int pageSize, String rnameStr) {
        //定义sql模板
        String sql="select * from tab_info_list where 1=1";
        List para=new ArrayList();//条件们
        StringBuilder sb = sqlTemplate(cid, rnameStr, para, sql);
        //添加分页条件
        para.add(start);
        para.add(pageSize);
        sb.append(" limit ? , ?");
        sql=sb.toString();
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ResultInfo.class),para.toArray());
    }

    /**
     * 定义sql模板
     * @param cid
     * @param rnameStr
     * @param para
     * @param sql
     * @return
     */
    private StringBuilder sqlTemplate(int cid, String rnameStr,List para,String sql){
        StringBuilder sb=new StringBuilder(sql);
        //判断参数是否有值
        if (cid!=0){
            sb.append(" and cid=?");
            para.add(cid);
        }
        if (rnameStr!=null&&rnameStr.length()>0){
            sb.append(" and iname like ?");
            para.add("%"+rnameStr+"%");
        }
        return sb;
    }
}
