package com.wb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;

public class DelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收数据
		String id = request.getParameter("id");
		//调用service层方法
		UserService service = new UserServiceImpl();
		int i = service.delUser(id);
		if(i > 0){
			response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");
			
		}else{
			response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet");
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
