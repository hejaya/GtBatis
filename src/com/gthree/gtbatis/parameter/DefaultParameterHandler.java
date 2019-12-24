package com.gthree.gtbatis.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Description
 * @Author Gthree
 * @Date 2019-12-24 15:04
 */
public class DefaultParameterHandler implements ParameterHandler{
    private Object parameter;

    public DefaultParameterHandler(Object parameter) {
        this.parameter = parameter;
    }

    @Override
    public void setParameters(PreparedStatement paramPreparedStatement) {
        if (null != parameter)
        {
            if (parameter.getClass().isArray())
            {
                Object[] params = (Object[])parameter;
                for (int i = 0; i < params.length; i++ )
                {
                    //TODO 参数类型转换
                    try {
                        paramPreparedStatement.setObject(i +1, params[i]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
