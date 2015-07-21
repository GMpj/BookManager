package com.librarymanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.librarymanager.dao.DbReader;
import com.librarymanager.model.BookingInfo;
import com.librarymanager.model.BorrowInfo;
import com.librarymanager.model.FineInfo;
import com.librarymanager.model.User;

/**
 * Servlet implementation class MyInfoServlet
 */
@WebServlet("/MyInfoServlet")
public class MyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyInfoServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int uid =  (int) request.getSession().getAttribute("logerId");
		String query = request.getParameter("query");

		if (query.equals("my")) {
			String mysql = "select * from user_info where user_id=" + uid;
			User user = DbReader.getBean(mysql, User.class);
			if (user == null) {
				PrintWriter out = response.getWriter();
				out.print("the user not exit");
			} else {
				request.setAttribute("user", user);
				request.getRequestDispatcher("m_user_info.jsp").forward(
						request, response);
				return;
			}
		} else if (query.equals("borrow")) {
			String borrowsql = "select * from borrow_info where user_id=" + uid;
			List<BorrowInfo> borrows = DbReader.getBeans(borrowsql,
					BorrowInfo.class);
			request.setAttribute("borrows", borrows);
			request.getRequestDispatcher("m_borrow.jsp").forward(request,
					response);
		} else if (query.equals("fine")) {
			String finesql = "select * from fine_info where user_id=" + uid;
			List<FineInfo> fines = DbReader.getBeans(finesql, FineInfo.class);
			request.setAttribute("fines", fines);
			request.getRequestDispatcher("m_fine.jsp").forward(request,
					response);
		} else if (query.equals("booking")) {
			String finesql = "select * from booking_info where user_id=" + uid;
			List<BookingInfo> bookings = DbReader.getBeans(finesql, BookingInfo.class);
			request.setAttribute("bookings", bookings);
			request.getRequestDispatcher("m_booking.jsp").forward(request,
					response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
