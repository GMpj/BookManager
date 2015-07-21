package com.librarymanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.librarymanager.dao.DbDelete;
import com.librarymanager.dao.DbReader;
import com.librarymanager.dao.DbWriter;
import com.librarymanager.model.Book;
import com.librarymanager.model.BorrowInfo;

/**
 * Servlet implementation class BookMnagerServlet
 */
@WebServlet("/BookMnagerServlet")
public class BookMnagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookMnagerServlet() {
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
		String manager = request.getParameter("manager");
		if (manager.equals("add")) {
			addBook(request, response);
		} else if (manager.equals("query")) {
			queryBook(request,response);
		}
		else if(manager.equals("delete")) {
			deleteBook(request,response);
		}
	}

	public void addBook(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String book_id = request.getParameter("book_id");
		String book_name = request.getParameter("book_name");
		String book_kind = request.getParameter("book_kind");
		String book_author = request.getParameter("book_author");
		String book_number = request.getParameter("book_number");
		System.out.println(book_id);
		String checksql = "select * from book_info where book_id=" + book_id;
		Book book = DbReader.getBean(checksql, Book.class);
		if (book == null) {
			String addsql = "insert into book_info (book_id,book_name,book_author,book_number,book_kind) values (?,?,?,?,?)";
			String params[] = { book_id, book_name, book_author, book_number,
					book_kind };
			int flag=DbWriter.write(addsql, params);
			if(flag==1){
				PrintWriter out = response.getWriter();
				out.print("add success");
			}else{
				PrintWriter out = response.getWriter();
				out.print("add faile");
			}
		
		} else {
			PrintWriter out = response.getWriter();
			out.print("the book id hava been used");
		}
	}

	public void queryBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String book_id = request.getParameter("book_id");
		String querysql="select * from book_info where book_id=" + book_id;
		Book book = DbReader.getBean(querysql, Book.class);
		request.setAttribute("book", book);
		 request.getRequestDispatcher("book_info.jsp").forward(request, response);
		
	}
	
	public void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		String book_id = request.getParameter("book_id");
		String querysql="select * from book_info where book_id=" + book_id;
		Book book = DbReader.getBean(querysql, Book.class);
		if(book==null){
			PrintWriter out = response.getWriter();
			out.print("the book not exit");
		}
		else {
			String checksql="select * from borrow_info where book_id="+book_id;
			List<BorrowInfo> borrows=DbReader.getBeans(checksql, BorrowInfo.class);
			if(borrows==null){
				
					String deletearticle="delete from article_info where book_id="+book_id;
					DbDelete.delete(deletearticle);
					String deletebooking="delete from booking_info where book_id="+book_id;
					DbDelete.delete(deletebooking);
					String deleteborrow="delete from borrow_info where book_id="+book_id;
					DbDelete.delete(deleteborrow);
					String deletefine="delete from fine_info where book_id="+book_id;
					DbDelete.delete(deletefine);
				
				String deletesql="delete from book_info where book_id="+book_id;
				int flag=DbDelete.delete(deletesql);
				if(flag==1){
					PrintWriter out = response.getWriter();
					out.print("success delete");
				}
				else {
					PrintWriter out = response.getWriter();
					out.print("faile delete");
				}
				
			}
			else{
				PrintWriter out = response.getWriter();
				out.print("the book has been borrowed,you can't delete");
			}
		}
	}
}
