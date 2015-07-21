package com.librarymanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.librarymanager.dao.DbDelete;
import com.librarymanager.dao.DbReader;
import com.librarymanager.dao.DbUpdate;
import com.librarymanager.dao.DbWriter;
import com.librarymanager.model.User;

/**
 * Servlet implementation class UserMangerServlet
 */
@WebServlet("/UserMangerServlet")
public class UserMangerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserMangerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
		String manager=request.getParameter("manager");
		if(manager.equals("delete")){
			deleteUser(request,response);
		}
		else if(manager.equals("add")){
			addUser(request,response);
		}
		else if(manager.equals("changepwd")){
			changepwd(request,response);
		}
		else if(manager.equals("login")){
			loginUser(request,response);
		}
	
	}

	public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String userId=request.getParameter("user_id");
		String checksql="select * from user_info where user_id="+userId;
		User user=DbReader.getBean(checksql, User.class);
		if(user==null){
			PrintWriter out = response.getWriter();
			out.print("the user not exit");
		}
		else {
			String deletebooking="delete from booking_info where userId="+userId;
			DbDelete.delete(deletebooking);
			String deleteborrow="delete from borrow_info where userId="+userId;
			DbDelete.delete(deleteborrow);
			String deletefine="delete from fine_info where userId="+userId;
			DbDelete.delete(deletefine);
			String deletesq="delete from user_info where user_id="+userId;
			int flag=DbDelete.delete(deletesq);
			if(flag==1){
				PrintWriter out = response.getWriter();
				out.print("success delete");
			}
			else{
				PrintWriter out = response.getWriter();
				out.print("faile delete");
			}
		}
	}
	
	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String user_id=request.getParameter("user_id");
		String user_name=request.getParameter("user_name");
		String user_paw=request.getParameter("user_paw");
		String user_number=request.getParameter("user_number");
		String checksql="select * from user_info where user_id="+user_id;
		User user=DbReader.getBean(checksql, User.class);
		if(user==null){
			String addsql="insert into user_info (user_id,user_name,user_paw,user_number) values (?,?,?,?)";
			String params[]={user_id,user_name,user_paw,user_number};
			DbWriter.write(addsql, params);
			PrintWriter out = response.getWriter();
			out.print("add success");
		}
		else{
			PrintWriter out = response.getWriter();
			out.print("the id hava been used");
		}
	}
	public void changepwd(HttpServletRequest request, HttpServletResponse response) throws IOException{
		int logerId= (int) request.getSession().getAttribute("logerId");
		String checksql="select * from user_info where user_id="+logerId;
		User user=DbReader.getBean(checksql, User.class);
		String pwd=request.getParameter("newpwd");
		String updatesql="update user_info set user_paw='"+pwd+"'";
		DbUpdate.update(updatesql);
		PrintWriter out = response.getWriter();
		out.print("success change password");
		
	}
	
	public void loginUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String user_id=request.getParameter("user_id");
		String pwd=request.getParameter("password");
		String sql="select * from user_info where user_id="+user_id+" and user_paw='"+pwd+"'";
		User user=DbReader.getBean(sql, User.class);
		if(user==null){
			PrintWriter out = response.getWriter();
			out.print("error userId or error password");
		}
		else{
			request.getSession().setAttribute("logerId", user.getUser_id());
			response.sendRedirect("index_user.html");
		}
	}
}
