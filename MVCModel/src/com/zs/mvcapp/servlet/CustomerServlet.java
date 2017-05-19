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


	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String idString = request.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(idString);
			customerDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("query.do");   //重定向到查询页面
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
		//3.request设置属性值
		request.setAttribute("customers", customers);
		//4.转发到index.jsp
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//1.获取请求的参数
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		//2.将请求参数封装成一个customer对象
		Customer customer = new Customer(name, address, phone);
		//2.1判断该对象是否在数据库中存在，若存在，则转发到newcustomer.jsp中，显示该名字已经被占用，请重新选择，return
		long count = customerDao.getCountWithName(name);
		if(count > 0){
			String msg = "填写的用户" + name + "已经被占用，请重新填写";
			request.setAttribute("message", msg);
			//这里是转发到newcustomer.jsp,把msg显示出来
			request.getRequestDispatcher("/newcustomer.jsp").forward(request, response);
			return;
		}
		//3.在数据库中不存在，调用customerDao的save方法，来到success.jsp
		customerDao.save(customer);
		response.sendRedirect("success.jsp");
		System.out.println("add");
	}

}
