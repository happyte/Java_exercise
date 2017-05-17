package com.zs.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String method = request.getParameter("method");
//		switch (method) {
//		case "add": add(request,response); break;
//		case "query": query(request,response); break;
//		case "delete": delete(request,response); break;
//		default: break;
//		}
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1);
		methodName = methodName.substring(0, methodName.length()-3); //获取对应的方法名,例如addCustomer
		try {
			// 利用反射获取指定方法
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("delete");
	}


	private void query(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("query");
	}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("add");
	}

}
