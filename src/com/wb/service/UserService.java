package com.wb.service;

import java.util.List;

import com.wb.domain.PW;
import com.wb.domain.PageBean;
import com.wb.domain.User;

public interface UserService {

	//登录
	PW loginUser(String username, String password);

	//查询所有
	List<User> findAll();

	//根据id查询
	User findUserById(String id);

	//根据id进行修改
	int updateUserById(User user);

	//添加
	int addUser(User user);

	//根据id删除
	int delUser(String id);

	//删除选中的多个
	int delByIds(String[] ids);

	//分页查询
	PageBean<User> findByPage(String currentPage, String rows);
	


}
