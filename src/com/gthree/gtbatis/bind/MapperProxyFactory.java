package com.gthree.gtbatis.bind;

import com.gthree.gtbatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * @Description 代理工厂，制造返回代理
 * @Author Gthree
 * @Date 2019-12-24 13:42
 */
public class MapperProxyFactory<T> {

    //接口实例
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    /**
     * 根据sqlSession 返回一个代理
     * @param sqlSession
     * @return
     */
    public T newInstance(SqlSession sqlSession)
    {
        MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession, this.mapperInterface);
        return newInstance1(mapperProxy);
    }

    /**
     *返回代理实例
     * @param mapperProxy
     * @return
     */
    private T newInstance1(MapperProxy<T> mapperProxy)
    {
        //Java动态代理
        //
        //loader: 用哪个类加载器去加载代理对象
        //
        //interfaces:动态代理类需要实现的接口
        //
        //动态代理方法在执行时，会调用里面的invoke方法去执行
        return (T) Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[] {this.mapperInterface}, mapperProxy);
    }
}
