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
import com.librarymanager.dao.DbWriter;
import com.librarymanager.model.Book;
import com.librarymanager.model.User;

/**
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
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
		String bookId=request.getParameter("book_id");
		String userId=request.getParameter("user_id");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String  date=df.format(new Date());
		
		String booksql="select * from book_info where book_id="+bookId;
		Book book=DbReader.getBean(booksql,Book.class);
		if(book==null){
			PrintWriter out = response.getWriter();
			out.print("the book not exit , you can't booking");
		}
		else{
		if(book.getBook_number()==0){
			String usersql="select * from user_info where user_id="+userId;
			User user=DbReader.getBean(usersql, User.class);
			if(user==null){
				PrintWriter out = response.getWriter();
				out.print("the user not exit , you can't booking");
			}
			else{
				String bookingsql="insert into booking_info (book_id,user_id,start_time) values (?,?,?)";
				String params[] = { bookId, userId, date};
				DbWriter.write(bookingsql,params);
				PrintWriter out = response.getWriter();
				out.print("booking success");
			}
		}
		else{
			PrintWriter out = response.getWriter();
			out.print("we have book you can borrow");
		}
		}
	}

}
