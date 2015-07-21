package com.librarymanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.librarymanager.dao.DbReader;
import com.librarymanager.dao.DbUpdate;
import com.librarymanager.model.BorrowInfo;

/**
 * Servlet implementation class ReturnServlet
 */
@WebServlet("/ReturnServlet")
public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReturnServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String bookId = request.getParameter("book_id");
		String userId = request.getParameter("user_id");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String borrowdate = df.format(new Date());
		PrintWriter out = response.getWriter();

		String checksql = "select * from borrow_info where book_id=" + bookId
				+ " and user_id=" + userId+" and return_state=0";
		BorrowInfo borrow = DbReader.getBean(checksql, BorrowInfo.class);
		if (borrow == null) {
			out.print("没有这条借书记录，无需还书");
		}
		else{
			if(borrow.getIslate()==0){
				String updatesql="update borrow_info set return_state=1 where borrow_id="+borrow.getBorrow_id();
				 DbUpdate.update(updatesql);
				 out.print("还书成功！");
			}
			else{
				String updatesql="update borrow_info set return_state=1 where borrow_id="+borrow.getBorrow_id();
				 DbUpdate.update(updatesql);
				 request.getRequestDispatcher("print_fine.html").forward(
							request, response);
			}
		}
	}

}
