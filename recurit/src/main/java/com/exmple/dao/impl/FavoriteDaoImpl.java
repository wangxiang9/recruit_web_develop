package com.exmple.dao.impl;

import com.exmple.dao.FavoriteDao;
import com.exmple.utils.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 添加一个收藏
     * @param iid
     * @param uid
     */
    @Override
    public void addFavorite(int iid, int uid) {
        //定义sql
        String sql="insert into tab_favorite (iid,uid) values (?,?)";
        jdbcTemplate.update(sql,iid,uid);
    }

    /**
     * 根据iid获取收藏次数
     * @param iid
     * @return
     */
    @Override
    public int queryCount(int iid) {
        //定义sql
        String sql="SELECT COUNT(*) FROM tab_favorite WHERE iid=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,iid);
    }

    /**
     * 看是否能查到这个数据
     * @param iid
     * @param uid
     * @return
     */
    @Override
    public int isFavorite(int iid, int uid) {
        //定义sql
        String sql="SELECT COUNT(*) FROM tab_favorite WHERE iid=? AND uid=?";
        return jdbcTemplate.queryForObject(sql,Integer.class,iid,uid);
    }
}
