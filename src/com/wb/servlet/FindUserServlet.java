package com.wb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.domain.User;
import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;

public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收用户需要修改数据的id
		String id = request.getParameter("id");
		UserService service = new UserServiceImpl();
		User user = service.findUserById(id);
		
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/update.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
