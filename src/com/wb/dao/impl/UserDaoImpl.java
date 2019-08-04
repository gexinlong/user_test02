package com.wb.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.wb.dao.UserDao;
import com.wb.domain.PW;
import com.wb.domain.User;
import com.wb.util.JDBCUtils;

public class UserDaoImpl implements UserDao {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

	//��¼
	@Override
	public PW loginUser(String username, String password) {
		try {
			String sql = "select * from pw where username = ? and password = ?";
			Object[] rsh = {username,password};
			return qr.query(sql, new BeanHandler<PW>(PW.class), rsh);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//��ѯ����
	@Override
	public List<User> findAll() {
		try {
			String sql = "select * from user";
			return qr.query(sql, new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//����id���в�ѯ
	@Override
	public User findUserById(int id) {
		try {
			String sql = "select * from user where id = ?";
			return qr.query(sql, new BeanHandler<User>(User.class),id);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	//����id�����޸�
	@Override
	public int updateUserById(User user) {
		try {
			String sql = "update user set name = ?,gender = ?,age = ?,address = ?,qq = ?,email  =? where id = ?";
			Object[] params = {user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId()};
		    return qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//���
/*	@Override
	public int addUser(User user) {
		try {
			String sql = "insert into user values(0,?,?,?,?,?,?)";
			Object[] params = {user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail()};
			return qr.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}*/
	
	@Override
	public int addUser(User user) {
		try {
			String sql = "insert into user values(0,?,?,?,?,?,?)";
			Object[]params = {user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail()};
			return qr.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//����idɾ��
	@Override
	public int delUser(int id) {
		try {
			String sql = "delete from user where id = ?";
			return qr.update(sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	//��ѯ�ܼ�¼��
	@Override
	public int findTotalCount() {
		try {
			String sql = "select count(*) from user";
			 Long count = qr.query(sql, new ScalarHandler<>());
			 return count.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0; 
		}
	}

	//ÿҳ��ʾ������
	@Override
	public List<User> findUserByPage(int start, int rows) {
		try {
			String sql = "select * from user limit ?,?";
			Object[] params = {start,rows};
			return qr.query(sql, new BeanListHandler<User>(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
