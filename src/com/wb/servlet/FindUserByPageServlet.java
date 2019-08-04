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
		//接收参数    当前页  和  当前页显示多少条数据
		String currentPage = request.getParameter("currentPage");
		String rows = request.getParameter("rows");
		
		//判断是否接收到参数
		if(currentPage == null || "".equals(currentPage)){
			currentPage = "1";
		}
		
		if(rows == null || "".equals(rows)){
			rows = "5";
		}
		
		UserService service = new UserServiceImpl();
		PageBean<User> pb = service.findByPage(currentPage,rows);
		
		//把PageBean放到域中
		request.setAttribute("pb", pb);
		System.out.println(pb);
		
		//request.getRequestDispatcher("/list.jsp").forward(request, response);
		//跳转到list.jsp页面
		request.getRequestDispatcher("/list.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
