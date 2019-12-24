package com.gthree.gtbatis.mapping;

import com.gthree.gtbatis.constants.Constant;

/**
 * @Description 维护了一条<select|update|delete|insert>节点的封装
 * @Author Gthree
 * @Date 2019-12-24 10:15
 */
public class MappedStatement {
    // mapper文件的namespace
    private String namespace;

    //sql的id属性
    private String sqlId;

    // sql语句，对应源码的sqlSource
    private String sql;

    // 返回类型
    private String resultType;

    // sqlCommandType对应select/update/insert等
    private Constant.SqlType sqlCommandType;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public Constant.SqlType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(Constant.SqlType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "namespace='" + namespace + '\'' +
                ", sqlId='" + sqlId + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                ", sqlCommandType=" + sqlCommandType +
                '}';
    }
}
