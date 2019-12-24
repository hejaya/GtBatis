package com.gthree.gtbatis.session.tacitly;

import com.gthree.gtbatis.common.CommonUtils;
import com.gthree.gtbatis.executor.Executor;
import com.gthree.gtbatis.executor.SimpleExecutor;
import com.gthree.gtbatis.mapping.MappedStatement;
import com.gthree.gtbatis.session.Configuration;
import com.gthree.gtbatis.session.SqlSession;

import java.util.List;

/**
 * @Description  默认的sqlsession实现
 * @Author Gthree
 * @Date 2019-12-24 14:21
 */
public class DefaultSqlSession implements SqlSession {

    private final Configuration configuration;

    private final Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new SimpleExecutor(configuration);
    }

    @Override
    public <T> T selectOne(String statementId, Object parameter) {
        List<T> results = this.<T> selectList(statementId, parameter);
        return CommonUtils.isNotEmpty(results) ? results.get(0) : null;//查询所有，返回一个
    }

    @Override
    public <E> List<E> selectList(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        return this.executor.<E>doQuery(mappedStatement,parameter);
    }

    @Override
    public void update(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public void insert(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public void delete(String statementId, Object parameter) {
        MappedStatement mappedStatement = this.configuration.getMappedStatement(statementId);
        this.executor.doUpdate(mappedStatement, parameter);
    }

    @Override
    public <T> T getMapper(Class<T> paramClass) {
        return configuration.<T> getMapper(paramClass,this);
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }
}
