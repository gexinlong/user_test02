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
		//首先处理乱码
		request.setCharacterEncoding("utf-8");
		//接收修改的数据
		Map<String, String[]> map = request.getParameterMap();
		
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//调用service方法 根据ID进行修改
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
