package com.exmple.dao.impl;

import com.exmple.dao.DetailInfoDao;
import com.exmple.domin.DetailInfo;
import com.exmple.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
        StringBuilder sb = sqlTemplate(cid, rnameStr, sql);
        sql=sb.toString();
        return jdbcTemplate.queryForObject(sql, Integer.class);
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
    public List<DetailInfo> queryList(int cid, int start, int pageSize, String rnameStr) {
//        List<DetailInfo> infoList=jdbcTemplate.query("select * from tab_info_list where 1=1 and cid=1 and iname like '%工%'  limit 0 , 10",new BeanPropertyRowMapper<>(DetailInfo.class));
//        for (DetailInfo d :
//                infoList) {
//            System.out.println(d);
//        }
        //定义sql模板
        String sql="select * from tab_info_list where 1=1";
        StringBuilder sb = sqlTemplate(cid, rnameStr, sql);
        //添加分页条件
        sb.append(" limit "+start+" , "+pageSize+"");
        sql=sb.toString();
        List<DetailInfo> infoList=jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DetailInfo.class));

        return infoList;
    }

    /**
     * 通过iid查询单个数据
     * @param iid
     * @return
     */
    @Override
    public DetailInfo queryOne(int iid) {
        //定义sql模板
        String sql="select * from tab_info_list where iid=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(DetailInfo.class),iid);
    }

    /**
     * 查询最新十条记录并返回
     * @param count
     * @return
     */
    @Override
    public List<DetailInfo> queryNewList(int count) {
        //定义sql
        String sql=null ;
        if (count<=10){
            sql="select * from tab_info_list";
        }else{//取最新10条记录
            sql="select * from tab_info_list order by iid desc limit 10";
        }
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DetailInfo.class));
    }

    /**
     * 查询收藏最多10条记录并返回
     * @param id_list
     * @return
     */
    @Override
    public List<DetailInfo> queryHotList(List<Integer> id_list) {
        //定义sql
        String sql="select * from tab_info_list where iid in(";
        StringBuilder sb=new StringBuilder(sql);
        for (Integer i :
                id_list) {
            sb.append(i).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        sql=sb.toString();
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(DetailInfo.class));
    }

    /**
     * 定义sql模板
     * @param cid
     * @param rnameStr
     * @param sql
     * @return
     */
    private StringBuilder sqlTemplate(int cid, String rnameStr, String sql){
        StringBuilder sb=new StringBuilder(sql);
        //判断参数是否有值
        if (cid!=0){
            sb.append(" and cid="+cid+"");
        }
        if (rnameStr!=null&&rnameStr.length()>0){
            sb.append(" and iname like '%"+rnameStr+"%' ");
        }
        return sb;
    }
}
