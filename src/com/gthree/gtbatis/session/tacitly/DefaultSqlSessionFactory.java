package com.gthree.gtbatis.session.tacitly;

import com.gthree.gtbatis.common.CommonUtils;
import com.gthree.gtbatis.common.Xml2MapperUtils;
import com.gthree.gtbatis.constants.Constant;
import com.gthree.gtbatis.session.Configuration;
import com.gthree.gtbatis.session.SqlSession;
import com.gthree.gtbatis.session.SqlSessionFactory;

import java.io.File;
import java.net.URL;

/**
 * @Description  默认的工厂实现
 * @Author Gthree
 * @Date 2019-12-24 10:23
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
        String path = Configuration.getProperty(Constant.MAPPER_LOCATION).replaceAll("\\.", "/");
        initMapperInfo(path);
    }

    /**
     * 解析xml文件
     * @param path 文件夹名字
     */
    private void initMapperInfo(String path) {
        URL url = DefaultSqlSessionFactory.class.getClassLoader().getResource(path);
        File confFile = new File(url.getFile());

        if (confFile.isDirectory()){
            File[] files = confFile.listFiles();
            if (CommonUtils.isNotEmpty(files)){
                for (File newFile : files) {
                    if (newFile.isDirectory()){
                        initMapperInfo(path +"/"+newFile.getName());//递归遍历下级
                    }else if (newFile.getName().endsWith(Constant.MAPPER_FILE_SUFFIX)){//只解析XML文件
                        Xml2MapperUtils.praseXML(newFile,configuration);
                    }
                }
            }
        }

    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(this.configuration);
    }
}
