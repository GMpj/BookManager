package com.librarymanager.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * Servlet implementation class BorrowServlet
 */
@WebServlet("/BorrowServlet")
public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowServlet() {
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
		String  borrowdate=df.format(new Date());
		Calendar c = Calendar.getInstance();  
        c.setTime(new Date());   //设置当前日期  
       
       
        String  returndate=df.format(new Date());
        PrintWriter out = response.getWriter();
		
        String booksql="select * from book_info where book_id="+bookId;
		Book book=DbReader.getBean(booksql,Book.class);
		if(book==null){
			out.print("the book not exit , you can't brrow");
		}
		else {
			
			if(book.getBook_number()==0){
				out.print("we have no the book you can booking");
			}
				else{
					String usersql="select * from user_info where user_id="+userId;
					User user=DbReader.getBean(usersql, User.class);
					if(user==null){
						out.print("the user not exit , you can't borrow");
					}
					else{
						if(user.getUser_number()<10){
							 if(book.getBook_kind().equals("图书"))
							        c.add(Calendar.MONTH, 3); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)  
							        else
							        	 c.add(Calendar.MONTH, 1);
							 Date date = c.getTime(); //结果
						String bookingsql="insert into borrow_info (user_id,book_id,borrow_time,return_time) values (?,?,?,?)";
						String params[] = { userId, bookId, borrowdate,returndate};
						DbWriter.write(bookingsql,params);
						out.print("borrow success!!");
						}
						else {
							out.print("you can't borrow book!!");
						}
						
					}
				}
			}
		}
	}


