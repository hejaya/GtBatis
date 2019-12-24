package com.gthree.test.mapper;

import com.gthree.test.model.User;

import java.util.List;

/**
 * @Description
 * @Author Gthree
 * @Date 2019-12-24 10:55
 */
public interface UserMapper {

    User getOne(Integer id);

    List<User> getAll();

    void updateByPrimaryKey(Integer id);

    void deleteByPrimaryKey(Integer id);
}
