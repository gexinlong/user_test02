package com.wb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;

public class DelSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] ids = request.getParameterValues("uid");
		UserService service = new UserServiceImpl();
		int i = service.delByIds(ids);
		if(i > 0){
			response.sendRedirect(request.getContextPath()+"/ListServlet");
		}else{
			response.sendRedirect(request.getContextPath()+"/ListServlet");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
