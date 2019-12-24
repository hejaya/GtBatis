package com.gthree.gtbatis.bind;

import com.gthree.gtbatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description mapper注册处理
 * @Author Gthree
 * @Date 2019-12-24 13:22
 */
public class MapperRegistry {

    //接口实例，相应的代理
    private final Map<Class<?>, MapperProxyFactory<?>> currMappers = new HashMap<>();

    /**
     * 注册代理工厂
     * @param type 接口的实例
     * @param <T>
     */
    public <T> void addMapper(Class<T> type)
    {
        this.currMappers.put(type, new MapperProxyFactory<T>(type));
    }

    /**
     * 获取代理工厂实例
     * @param type
     * @param sqlSession
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> type, SqlSession sqlSession)
    {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>)this.currMappers.get(type);

        return mapperProxyFactory.newInstance(sqlSession);
    }
}
