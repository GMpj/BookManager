package com.librarymanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.librarymanager.dao.DbReader;
import com.librarymanager.dao.DbWriter;
import com.librarymanager.model.BorrowInfo;

/**
 * Servlet implementation class FineServlet
 */
@WebServlet("/FineServlet")
public class FineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		String userId = request.getParameter("user_id");
		String reason=request.getParameter("reason");
		String amount=request.getParameter("amount");
		PrintWriter out = response.getWriter();
		String checksql = "select * from borrow_info where book_id=" + bookId
				+ " and user_id=" + userId;
		BorrowInfo borrow = DbReader.getBean(checksql, BorrowInfo.class);
		if(borrow==null){
			out.print("can't find the record");
		}
		else{
			String finesql="insert into fine_info (user_id,fine_reason,fine_amount,book_id) values (?,?,?,?)";
			String params[]={userId,reason,amount,bookId};
			
			int flag=DbWriter.write(finesql, params);
			if(flag==1)
			out.print("sucessful");
			else 
				out.print("罚单打印失败");
		}
	}

}
