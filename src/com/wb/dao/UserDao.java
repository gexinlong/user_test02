package com.wb.dao;

import java.util.List;

import com.wb.domain.PW;
import com.wb.domain.User;

public interface UserDao {

	//��¼
	PW loginUser(String username, String password);

	//��ѯ����
	List<User> findAll();

	//����id���в�ѯ
	User findUserById(int id);

	//����id�����޸�
	int updateUserById(User user);

	//���
	int addUser(User user);

	//����idɾ��
	int delUser(int id);

	//��ѯ�ܼ�¼��
	int findTotalCount();

	//ÿҳ��ʾ������
	List<User> findUserByPage(int start, int rows);
	

}
