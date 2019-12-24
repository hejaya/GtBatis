package com.gthree.gtbatis.bind;

import com.gthree.gtbatis.mapping.MappedStatement;
import com.gthree.gtbatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @Description 代理对象
 * @Author Gthree
 * @Date 2019-12-24 13:22
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final SqlSession sqlSession;

    private final Class<T> mapperInterface;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {//method.getDeclaringClass() 表示的方法的类的Class对象。
            return method.invoke(o, objects);
        }
        return this.execute(method, objects);//执行curd方法
    }

    /**
     * 执行sql
     *
     * @param method
     * @param objects
     * @return
     */
    private Object execute(Method method, Object[] objects) {

        Object result = null;//结果集
        //mapper类全名+方法名
        String statementId = this.mapperInterface.getName() + "." + method.getName();
        MappedStatement mappedStatement = this.sqlSession.getConfiguration().getMappedStatement(statementId);

        switch (mappedStatement.getSqlCommandType()) {
            case SELECT: {
                Class<?> returnType = method.getReturnType();//得到返回类型
                // 如果返回的是list，应该调用查询多个结果的方法，否则只要查单条记录
                if (Collection.class.isAssignableFrom(returnType)) {
                    result = sqlSession.selectList(statementId, objects);
                } else {
                    result = sqlSession.selectOne(statementId, objects);
                }
                break;
            }
            case UPDATE: {
                sqlSession.update(statementId,objects);
                break;
            }
            case INSERT: {
                //TODO 待处理插入
                break;
            }
            case DEFAULT: {

                break;
            }
            default: {

            }
        }
        return result;
    }
}
