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
		//���Ƚ������봦��
		request.setCharacterEncoding("utf-8");
		//��������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//��Ҫ�����û��������֤�� ��  �������Լ���������֤��  Ȼ����бȽ�
		//�����û������Ķ�ά��
		String verifycode = request.getParameter("verifycode");
		//���շ������Լ���������֤��
		String checkcode = (String)request.getSession().getAttribute("CHECKCODE_SERVER");
		
		if(checkcode.equalsIgnoreCase(verifycode)){
			//��֤����ȷ  �ڶ��û�������������ж�
			UserService service = new UserServiceImpl();
			PW pw = service.loginUser(username,password);
			System.out.println(pw);
			if(pw != null){
				//�û���������ȷ  ��ת����ҳ��
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}else{
				//��¼ʧ��  ��ת����¼���� ����������ʾ
				request.setAttribute("eroMsg", "�û������������");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}
		}else{
			//��֤�����ת����¼���棬�����������ʾ��Ϣ
			request.getSession().setAttribute("eroMsg", "��֤�����");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
