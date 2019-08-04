package com.wb.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wb.domain.PW;
import com.wb.service.UserService;
import com.wb.service.impl.UserServiceImpl;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//首先进行乱码处理
		request.setCharacterEncoding("utf-8");
		//接收数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//还要接受用户输入的验证码 和  服务器自己产生的验证码  然后进行比较
		//接收用户产生的二维码
		String verifycode = request.getParameter("verifycode");
		//接收服务器自己产生的验证码
		String checkcode = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
		
		if(checkcode.equalsIgnoreCase(verifycode)){
			//验证码正确  在对用户名和密码进行判断
			UserService service = new UserServiceImpl();
			PW pw = service.loginUser(username,password);
			System.out.println(pw);
			if(pw != null){
				//用户名密码正确  跳转到首页面
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else{
				//登录失败  跳转到登录界面 给出错误提示
				request.setAttribute("eroMsg", "用户名或密码错误");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}
		}else{
			//验证码错误，转到登录界面，给出错误的提示信息
			request.getSession().setAttribute("eroMsg", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
