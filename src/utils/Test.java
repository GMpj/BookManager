package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.librarymanager.dao.DbDelete;
import com.librarymanager.dao.DbReader;
import com.librarymanager.dao.DbUpdate;
import com.librarymanager.dao.DbWriter;
import com.librarymanager.model.Book;
import com.librarymanager.model.User;

import junit.framework.TestCase;

public class Test extends TestCase{
 public void testDbReader(){
	 String name="数据库";
	 String sql="select * from book_info where book_name like '"+name+"%'";
	 List<Book> books=DbReader.getBeans(sql, Book.class);
	 System.out.println(books.size());
	 for(Book book:books){
		 System.out.println(book.getBook_name());
	 }
 }
// public void testDbDelete(){
//	 String sql="delete from user where id=1";
//	 DbDelete.delete(sql);
// }
// public void testDbWriter()
// {
//	 String sql="insert user value(10,'pj',3)";
//	 DbWriter.write(sql);
// }
// public void testDbUpdate(){
//	 String sql="update user set age=30 where id=10";
//	 DbUpdate.update(sql);
// }
 public void testdate(){
	
//	 Calendar parent_time=Calendar.getInstance();
//	   parent_time.setTime(new Date());
	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      
	   System.out.println(df.format(new Date()));
 }
 
// public void testWrite(){
//	 String bookId="1";
//	 String userId="123";
//	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		String  date=df.format(new Date());
//	 String bookingsql="insert into booking_info (book_id,user_id,start_time,close_state) values (?,?,?,?)";
//		String params[] = { bookId, userId, date,"1"};
//		DbWriter.write(bookingsql,params);
// }
 public void testDate(){
	 Calendar c = Calendar.getInstance();  
     c.setTime(new Date());   //设置当前日期  
     c.add(Calendar.MONTH, 3); //日期分钟加1,Calendar.DATE(天),Calendar.HOUR(小时)  
     Date date = c.getTime(); //结果
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
     
	   System.out.println(df.format(date));
    
 }
 public void testUpdate(){
	 String borrowId="1";
	 String updatesql="update borrow_info set return_state=1 where borrow_id="+borrowId;
	 DbUpdate.update(updatesql);
 }
}
