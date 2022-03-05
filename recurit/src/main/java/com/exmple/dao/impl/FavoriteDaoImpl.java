package com.exmple.dao.impl;

import com.exmple.dao.FavoriteDao;
import com.exmple.domin.Favorite;
import com.exmple.utils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

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
    public int queryCount(int iid,String name) {
        //定义sql
        String sql="SELECT COUNT(*) FROM tab_favorite WHERE "+name+"=?";
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

    /**
     * 同过uid查询iid集合
     * @param uid
     * @param start
     * @param pageSize
     * @return
     */
    @Override
    public List<Favorite> queryIds(int uid, int start, int pageSize) {
        try {
            //定义sql
            String sql="SELECT * FROM tab_favorite WHERE uid=? limit ?,?";
            return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Favorite.class),uid,start,pageSize);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
