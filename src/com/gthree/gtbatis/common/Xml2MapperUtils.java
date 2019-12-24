package com.gthree.gtbatis.common;

import com.gthree.gtbatis.constants.Constant;
import com.gthree.gtbatis.mapping.MappedStatement;
import com.gthree.gtbatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @Author Gthree
 * @Date 2019-12-24 11:27
 */
public final class Xml2MapperUtils {

    /**
     * 解析XML
     *
     * @param file
     * @param configuration
     */
    public static void praseXML(File file, Configuration configuration) {
        // 创建一个读取器
        SAXReader saxReader = new SAXReader();
        saxReader.setEncoding(Constant.CHARSET_UTF8);

        try {
            // 读取文件内容
            Document document = null;

            document = saxReader.read(file);

            // 获取xml中的根元素
            Element rootElement = document.getRootElement();

            if (!Constant.XML_ROOT_LABEL.equals(rootElement.getName())) {
                throw new RuntimeException("xml文件根元素不是mapper");
            }

            //获取命名空间
            String nameSpace = rootElement.attributeValue(Constant.XML_SELECT_NAMESPACE);

            List<MappedStatement> mappedStatements = new ArrayList<>();

            for (Iterator iterator = rootElement.elementIterator(); iterator.hasNext(); ) {
                Element element = (Element) iterator.next();
                String elName = element.getName();
                MappedStatement statement = new MappedStatement();

                if (Constant.SqlType.SELECT.value().equals(elName)) {//select语句
                    String resultType = element.attributeValue(Constant.XML_SELECT_RESULTTYPE);
                    statement.setResultType(resultType);
                    statement.setSqlCommandType(Constant.SqlType.SELECT);
                } else if (Constant.SqlType.UPDATE.value().equals(elName)) {//update类型
                    statement.setSqlCommandType(Constant.SqlType.UPDATE);
                } else if (Constant.SqlType.INSERT.value().equals(elName)) {//insert类型
                    statement.setSqlCommandType(Constant.SqlType.INSERT);
                } else {
                    statement.setSqlCommandType(Constant.SqlType.DEFAULT);
                    throw new RuntimeException("不支持此xml标签解析");
                }

                //设置SQL的唯一ID
                String sqlId = nameSpace + "." + element.attributeValue(Constant.XML_ELEMENT_ID);

                statement.setSqlId(sqlId);
                statement.setNamespace(nameSpace);
                statement.setSql(CommonUtils.stringTrim(element.getStringValue()));

                mappedStatements.add(statement);

                configuration.addMappedStatement(sqlId, statement);

                configuration.addMapper(Class.forName(nameSpace));//对应命名空间，接口的全限定类名，如com.gthree.test.mapper.UserMapper，反射生成实例
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
