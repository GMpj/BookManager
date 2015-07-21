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
import com.librarymanager.model.Article;
import com.librarymanager.model.Book;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
	    String search_kind=request.getParameter("book_kind");
	    if(search_kind.equals("书刊")){
	    	searchBook(request,response);
	    }
	    else if(search_kind.equals("文章")){
	    	searchArticle(request,response);
	    }
	    else{
			PrintWriter out = response.getWriter();
			out.print("error");
	    }
	}
 private void searchBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	 String book_name=request.getParameter("book_name");
	 String sql="select * from book_info where book_name like '"+book_name+"%'";
	 List<Book> books=DbReader.getBeans(sql, Book.class);
	 if(books==null){
		 PrintWriter out = response.getWriter();
			out.print("not exit the book");
	 }
	 else{
		 System.out.println(books.size());
	 request.setAttribute("books", books);
	 request.getRequestDispatcher("book_list.jsp").forward(request, response);
	 }
 }
 private void searchArticle(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	 String book_name=request.getParameter("book_name");
	 String sql="select * from article_info where article_name like '"+book_name+"%'";
	 List<Article> articles=DbReader.getBeans(sql, Article.class);
	 if(articles==null){
		 PrintWriter out = response.getWriter();
			out.print("not exit the article");
	 }
	 else{
	 request.setAttribute("articles", articles);
	 request.getRequestDispatcher("book_list.jsp").forward(request, response);
	 }
 }
}
