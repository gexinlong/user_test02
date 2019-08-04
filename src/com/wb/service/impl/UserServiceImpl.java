package com.wb.service.impl;

import java.util.List;

import com.wb.dao.UserDao;
import com.wb.dao.impl.UserDaoImpl;
import com.wb.domain.PW;
import com.wb.domain.PageBean;
import com.wb.domain.User;
import com.wb.service.UserService;

public class UserServiceImpl implements UserService {
	UserDao dao = new UserDaoImpl();

	//登录
	@Override
	public PW loginUser(String username, String password) {
		return dao.loginUser(username,password);
	}

	//查询所有
	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	//根据id查询
	@Override
	public User findUserById(String strId) {
		int id = Integer.parseInt(strId);
		return dao.findUserById(id);
	}

	//根据id进行修改
	@Override
	public int updateUserById(User user) {
		return dao.updateUserById(user);
	}

	//添加
	@Override
	public int addUser(User user) {
		return dao.addUser(user);
	}

	//根据id删除
	@Override
	public int delUser(String StrId) {
		int id = Integer.parseInt(StrId);
		return dao.delUser(id);
	}

	//删除选中的多个
	@Override
	public int delByIds(String[] ids) {
		int count = 0;
		for (String strId : ids) {
			int id = Integer.parseInt(strId);
			count += dao.delUser(id);
		}
		return count;
	}

	//分页查询
	@Override
	public PageBean<User> findByPage(String currentPageStr, String rowsStr) {
		//创建PageBean对象
		PageBean<User> pageBean = new PageBean<>();
		//把当前页和每页显示多少条数据转为整型
		int currentPage = Integer.parseInt(currentPageStr);
		//如果当前页小于等于0   把当前页赋值为第一页
		if(currentPage <= 0){
			currentPage = 1;
		}
		int rows = Integer.parseInt(rowsStr);
		//调用PageBean的set方法给currentPage和rows设置值
		pageBean.setCurrentPage(currentPage);
		pageBean.setRows(rows);
		
		//设置总记录数
		//调用dao层的方法查询到总记录数
		int totalCount = dao.findTotalCount();
		//把从数据库查询到有多少条记录数   用PageBean对象的set方法给总记录数设置值
		pageBean.setTotalCount(totalCount);
		
		//然后设置总页数
		int totalPage = totalCount%rows == 0 ? totalCount/rows : totalCount/rows+1;
		pageBean.setTotalPage(totalPage);
		
		//设置每页显示的数据  每页显示多条数据所以放在list集合中   显示的数据要从数据库中去查
		//开始索引和每页显示多少条数据 
		int start = (currentPage-1)*rows;
		List<User> list = dao.findUserByPage(start,rows);
		//再把查到的list设置给PageBean
		pageBean.setList(list);
		
		return pageBean;
	}
	

}
