package com.gthree.gtbatis.resultset;

import com.gthree.gtbatis.mapping.MappedStatement;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description  结果处理器
 * @Author Gthree
 * @Date 2019-12-24 15:13
 */
public class DefaultResultSetHandler implements ResultSetHandler {

    private final MappedStatement mappedStatement;

    public DefaultResultSetHandler(MappedStatement mappedStatement) {
        this.mappedStatement = mappedStatement;
    }

    @Override
    public <E> List<E> handleResultSets(ResultSet resultSet) {

        List<E> result = new ArrayList<>();

        if (null == resultSet) {
            return null;
        }

        try {
            while (resultSet.next()) {
                //TODO 要求属性名和字段名一直
                Class<?> modelClass = Class.forName(mappedStatement.getResultType());//反射得到返回类型
                E model = (E) modelClass.newInstance();

                Field[] declaredFields = modelClass.getDeclaredFields();//得到属性
                for (Field field : declaredFields) {
                    // 对成员变量赋值
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    if (String.class.equals(fieldType)) {
                        field.set(model, resultSet.getString(field.getName()));
                    } else if (int.class.equals(fieldType) || Integer.class.equals(fieldType)) {
                        field.set(model, resultSet.getInt(field.getName()));
                    } else {
                        //TODO 其他类型自己转换，当前默认处理
                        field.set(model, resultSet.getObject(field.getName()));
                    }
                    result.add(model);
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
