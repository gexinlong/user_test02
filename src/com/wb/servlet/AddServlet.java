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

public class AddServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理乱码
		request.setCharacterEncoding("utf-8");
		//接收用户添加的数据
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		UserService service = new UserServiceImpl();
		
		int i = service.addUser(user);
		if(i > 0 ){
			request.getRequestDispatcher("/ListServlet").forward(request, response);
		}else{
			//request.getRequestDispatcher("/add.jsp").forward(request, response);
			response.sendRedirect(request.getContextPath()+"/add.jsp");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
