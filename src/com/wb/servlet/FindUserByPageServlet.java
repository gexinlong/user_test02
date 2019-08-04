package com.wb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.domain.PageBean;
import com.wb.domain.User;
import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;

public class FindUserByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ղ���    ��ǰҳ  ��  ��ǰҳ��ʾ����������
		String currentPage = request.getParameter("currentPage");
		String rows = request.getParameter("rows");
		
		//�ж��Ƿ���յ�����
		if(currentPage == null || "".equals(currentPage)){
			currentPage = "1";
		}
		
		if(rows == null || "".equals(rows)){
			rows = "5";
		}
		
		UserService service = new UserServiceImpl();
		PageBean<User> pb = service.findByPage(currentPage,rows);
		
		//��PageBean�ŵ�����
		request.setAttribute("pb", pb);
		System.out.println(pb);
		
		//request.getRequestDispatcher("/list.jsp").forward(request, response);
		//��ת��list.jspҳ��
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
