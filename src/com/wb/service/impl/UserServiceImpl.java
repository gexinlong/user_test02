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

	//��¼
	@Override
	public PW loginUser(String username, String password) {
		return dao.loginUser(username,password);
	}

	//��ѯ����
	@Override
	public List<User> findAll() {
		return dao.findAll();
	}

	//����id��ѯ
	@Override
	public User findUserById(String strId) {
		int id = Integer.parseInt(strId);
		return dao.findUserById(id);
	}

	//����id�����޸�
	@Override
	public int updateUserById(User user) {
		return dao.updateUserById(user);
	}

	//���
	@Override
	public int addUser(User user) {
		return dao.addUser(user);
	}

	//����idɾ��
	@Override
	public int delUser(String StrId) {
		int id = Integer.parseInt(StrId);
		return dao.delUser(id);
	}

	//ɾ��ѡ�еĶ��
	@Override
	public int delByIds(String[] ids) {
		int count = 0;
		for (String strId : ids) {
			int id = Integer.parseInt(strId);
			count += dao.delUser(id);
		}
		return count;
	}

	//��ҳ��ѯ
	@Override
	public PageBean<User> findByPage(String currentPageStr, String rowsStr) {
		//����PageBean����
		PageBean<User> pageBean = new PageBean<>();
		//�ѵ�ǰҳ��ÿҳ��ʾ����������תΪ����
		int currentPage = Integer.parseInt(currentPageStr);
		//�����ǰҳС�ڵ���0   �ѵ�ǰҳ��ֵΪ��һҳ
		if(currentPage <= 0){
			currentPage = 1;
		}
		int rows = Integer.parseInt(rowsStr);
		//����PageBean��set������currentPage��rows����ֵ
		pageBean.setCurrentPage(currentPage);
		pageBean.setRows(rows);
		
		//�����ܼ�¼��
		//����dao��ķ�����ѯ���ܼ�¼��
		int totalCount = dao.findTotalCount();
		//�Ѵ����ݿ��ѯ���ж�������¼��   ��PageBean�����set�������ܼ�¼������ֵ
		pageBean.setTotalCount(totalCount);
		
		//Ȼ��������ҳ��
		int totalPage = totalCount%rows == 0 ? totalCount/rows : totalCount/rows+1;
		pageBean.setTotalPage(totalPage);
		
		//����ÿҳ��ʾ������  ÿҳ��ʾ�����������Է���list������   ��ʾ������Ҫ�����ݿ���ȥ��
		//��ʼ������ÿҳ��ʾ���������� 
		int start = (currentPage-1)*rows;
		List<User> list = dao.findUserByPage(start,rows);
		//�ٰѲ鵽��list���ø�PageBean
		pageBean.setList(list);
		
		return pageBean;
	}
	

}
