package com.gthree.gtbatis.session;

import com.gthree.gtbatis.session.tacitly.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description 构建工厂
 * @Author Gthree
 * @Date 2019-12-24 10:10
 */
public class SqlSessionFactoryBuilder {

    /**
     *
     * @param fileName 文件路径
     * @return
     */
    public SqlSessionFactory build(String fileName){

        InputStream inputStream = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);
        try {
            Configuration.PROPS.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new DefaultSqlSessionFactory(new Configuration());
    }
}
