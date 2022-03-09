package com.exmple.service.impl;

import com.exmple.dao.CategoryDao;
import com.exmple.dao.impl.CategoryDaoImpl;
import com.exmple.domin.Category;
import com.exmple.service.CategoryService;
import com.exmple.utils.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao=new CategoryDaoImpl();

    /**
     * 查询分类信息
     * @return
     */
    @Override
    public List<Category> findAll() {
        //获取jedis客户端
        Jedis jedis = JedisUtil.getJedis();
        //使用sortedset排序查询
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);
        List<Category>list=null;
        //判断集合是否存在category
        if (category==null||category.size()==0){//集合为空从数据库中查询
            list=categoryDao.findAll();
            //储存在redis中
            for (Category c :
                    list) {
                jedis.zadd("category",c.getCid(),c.getCname());
            }
            //重新从缓存中读取
            category = jedis.zrangeWithScores("category", 0, -1);
            list=getInfoFromRedis(category);
        }else {//集合不为空封装成list返回
            list=getInfoFromRedis(category);
        }
        return list;
    }

    /**
     * 将缓存中的数据set封装成list
     * @param category
     * @return
     */
    private List<Category> getInfoFromRedis(Set<Tuple> category){
        List<Category> list=new ArrayList<Category>();
        for (Tuple tuple :
                category) {
            Category c = new Category();
            c.setCname(tuple.getElement());
            c.setCid((int)tuple.getScore());
            list.add(c);
        }
        return list;
    }
}
