package com.wb.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.domain.User;
import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;

public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService service = new UserServiceImpl();
		List<User> list = service.findAll();
		System.out.println(list);
		 
		 //�Ѳ鵽�����ݷŵ�����
		 request.getSession().setAttribute("list", list);
		 
		 //��ת��list.jspҳ��
		 //request.getRequestDispatcher("/list.jsp").forward(request, response);
		 
		//��ת�� list.jsp
		 response.sendRedirect(request.getContextPath()+"/list.jsp");
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}
