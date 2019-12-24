package com.gthree.gtbatis.executor;

import com.gthree.gtbatis.constants.Constant;
import com.gthree.gtbatis.mapping.MappedStatement;
import com.gthree.gtbatis.parameter.DefaultParameterHandler;
import com.gthree.gtbatis.parameter.ParameterHandler;
import com.gthree.gtbatis.resultset.DefaultResultSetHandler;
import com.gthree.gtbatis.resultset.ResultSetHandler;
import com.gthree.gtbatis.session.Configuration;
import com.gthree.gtbatis.statement.SimpleStatementHandler;
import com.gthree.gtbatis.statement.StatementHandler;

import java.sql.*;
import java.util.List;

/**
 * @Description
 * @Author Gthree
 * @Date 2019-12-24 14:23
 */
public class SimpleExecutor implements Executor {

    //数据库连接
    private static Connection connection;

    private Configuration configuration;

    static {
        initConnect();
    }

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> doQuery(MappedStatement ms, Object parameter) {
        try {
            //获取数据库连接
            Connection connect = getConnect();
            //得到sql信息
            MappedStatement mappedStatement = configuration.getMappedStatement(ms.getSqlId());
            //实例化StatementHandler对象，
            StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);
            //通过StatementHandler和connection获取PreparedStatement
            PreparedStatement preparedStatement = statementHandler.prepare(connection);
            //实例化ParameterHandler，将SQL语句中？参数注入
            ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
            parameterHandler.setParameters(preparedStatement);
            //执行SQL，得到结果集ResultSet
            ResultSet resultSet = statementHandler.query(preparedStatement);
            //实例化ResultSetHandler，通过反射将ResultSet中结果设置到目标resultType对象中
            ResultSetHandler resultSetHandler = new DefaultResultSetHandler(mappedStatement);
            /*while(resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String pwd = resultSet.getString(3);
                System.out.println("id:"+id+" 姓名："+name+" 密码："+pwd);
            }*/
            return resultSetHandler.handleResultSets(resultSet);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void doUpdate(MappedStatement ms, Object parameter) {
        try
        {
            Connection connection = getConnect();

            MappedStatement mappedStatement = configuration.getMappedStatement(ms.getSqlId());

            StatementHandler statementHandler = new SimpleStatementHandler(mappedStatement);

            PreparedStatement preparedStatement = statementHandler.prepare(connection);

            ParameterHandler parameterHandler = new DefaultParameterHandler(parameter);
            parameterHandler.setParameters(preparedStatement);

            statementHandler.update(preparedStatement);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //初始化连接
    private static void initConnect() {
        String driver = Configuration.getProperty(Constant.DB_DRIVER_CONF);
        String url = Configuration.getProperty(Constant.DB_URL_CONF);
        String username = Configuration.getProperty(Constant.DB_USERNAME_CONF);
        String password = Configuration.getProperty(Constant.DB_PASSWORD);

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection getConnect() throws SQLException {
        if (null != connection) {
            return connection;
        } else {
            throw new SQLException("无法连接数据库");
        }
    }
}
