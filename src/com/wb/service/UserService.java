package com.wb.service;

import java.util.List;

import com.wb.domain.PW;
import com.wb.domain.PageBean;
import com.wb.domain.User;

public interface UserService {

	//��¼
	PW loginUser(String username, String password);

	//��ѯ����
	List<User> findAll();

	//����id��ѯ
	User findUserById(String id);

	//����id�����޸�
	int updateUserById(User user);

	//���
	int addUser(User user);

	//����idɾ��
	int delUser(String id);

	//ɾ��ѡ�еĶ��
	int delByIds(String[] ids);

	//��ҳ��ѯ
	PageBean<User> findByPage(String currentPage, String rows);
	


}
