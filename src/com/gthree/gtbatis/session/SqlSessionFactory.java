package com.gthree.gtbatis.session;

/**
 * @Description 工厂
 * @Author Gthree
 * @Date 2019-12-24 10:07
 */
public interface SqlSessionFactory {
    /**
     * 打开会话工厂
     * @return
     */
    SqlSession openSession();
}
