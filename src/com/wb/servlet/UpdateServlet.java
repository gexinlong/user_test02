package com.wb.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wb.domain.User;
import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;


public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ȴ�������
		request.setCharacterEncoding("utf-8");
		//�����޸ĵ�����
		Map<String, String[]> map = request.getParameterMap();
		
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//����service���� ����ID�����޸�
		UserService service = new UserServiceImpl();
		int i = service.updateUserById(user);
		if(i > 0){
			request.getRequestDispatcher("/ListServlet").forward(request, response);
		}else{
			request.getRequestDispatcher("/ListServlet?id="+user.getId()).forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
