package com.zs.mvcapp.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zs.mvcapp.dao.CustomerDAO;
import com.zs.mvcapp.dao.impl.CustomerDaoJdbcImpl;
import com.zs.mvcapp.domain.CriteriaCustomer;
import com.zs.mvcapp.domain.Customer;


@WebServlet("*.do")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static CustomerDAO customerDao = new CustomerDaoJdbcImpl();  //实例化接口类对象

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


	private void query(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		//1.发送请求来到query方法，获取所有Customer对象
//		List<Customer> customers = customerDao.getAll();
//		//2.request设置属性值
//		request.setAttribute("customers", customers);
//		//3.重定向到index.jsp
//		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		//1.获取模糊查询的name,phone,address,创建CriteriaCustomer对象
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		CriteriaCustomer cc = new CriteriaCustomer(name, address, phone);
		//2.获取所有满足模糊查询条件的对象
		List<Customer> customers = customerDao.getForListWithCriteriaCustomer(cc);
		System.out.println("customers:"+customers);
		//3.request设置属性值
		request.setAttribute("customers", customers);
		//4.重定向到index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("add");
	}

}
