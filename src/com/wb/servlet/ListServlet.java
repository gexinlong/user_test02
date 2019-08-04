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
		 
		 //把查到的数据放到域中
		 request.getSession().setAttribute("list", list);
		 
		 //跳转到list.jsp页面
		 //request.getRequestDispatcher("/list.jsp").forward(request, response);
		 
		//跳转到 list.jsp
		 response.sendRedirect(request.getContextPath()+"/list.jsp");
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}
