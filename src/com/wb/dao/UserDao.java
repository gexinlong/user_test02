package com.wb.dao;

import java.util.List;

import com.wb.domain.PW;
import com.wb.domain.User;

public interface UserDao {

	//登录
	PW loginUser(String username, String password);

	//查询所有
	List<User> findAll();

	//根据id进行查询
	User findUserById(int id);

	//根据id进行修改
	int updateUserById(User user);

	//添加
	int addUser(User user);

	//根据id删除
	int delUser(int id);

	//查询总记录数
	int findTotalCount();

	//每页显示的数据
	List<User> findUserByPage(int start, int rows);
	

}
