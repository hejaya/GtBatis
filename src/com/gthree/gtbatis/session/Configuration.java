package com.gthree.gtbatis.session;

import com.gthree.gtbatis.bind.MapperRegistry;
import com.gthree.gtbatis.mapping.MappedStatement;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Description 核心配置类
 * @Author Gthree
 * @Date 2019-12-24 10:14
 */
public class Configuration {

    //配置项，加载配置文件
    public static Properties PROPS = new Properties();

    // mapper代理注册器
    protected final MapperRegistry mapperRegistry = new MapperRegistry();

    // mapper文件的select/update语句的id和SQL语句属性
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();


    /**
     *
     * @param type
     * @param <T> 接口的实例
     */
    public <T> void addMapper(Class<T> type)
    {
        this.mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession)
    {
        return this.mapperRegistry.getMapper(type, sqlSession);
    }

    /**
     *
     * @param key
     * @param mappedStatement
     */
    public void addMappedStatement(String key, MappedStatement mappedStatement)
    {
        this.mappedStatements.put(key, mappedStatement);
    }

    /**
     * 获取MappedStatement
     *
     * @param id xml文件标签的id属性
     * @return
     * @see
     */
    public MappedStatement getMappedStatement(String id)
    {
        return this.mappedStatements.get(id);
    }

    /**
     * 获取字符串属性值
     * @param key
     * @return
     */
    public static String getProperty(String key)
    {
        return getProperty(key, "");
    }


    /**
     * 获取属性值
     * @param key 键值
     * @param defaultValue 默认值
     * @return
     */
    public static String getProperty(String key, String defaultValue)
    {
        return PROPS.containsKey(key) ? PROPS.getProperty(key) : defaultValue;
    }

}
