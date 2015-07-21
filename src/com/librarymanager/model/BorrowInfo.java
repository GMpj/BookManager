package com.librarymanager.model;

import java.util.Date;

public class BorrowInfo {
	private int borrow_id;
	private int user_id;
	private int book_id;
	private Date borrow_time;
	private Date return_time;
	private int return_state;
	private int islate;

	public int getBorrow_id() {
		return borrow_id;
	}
	public void setBorrow_id(int borrow_id) {
		this.borrow_id = borrow_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public Date getBorrow_time() {
		return borrow_time;
	}
	public void setBorrow_time(Date borrow_time) {
		this.borrow_time = borrow_time;
	}
	public Date getReturn_time() {
		return return_time;
	}
	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}
	public int getReturn_state() {
		return return_state;
	}
	public void setReturn_state(int return_state) {
		this.return_state = return_state;
	}
	public int getIslate() {
		return islate;
	}
	public void setIslate(int islate) {
		this.islate = islate;
	}

}
