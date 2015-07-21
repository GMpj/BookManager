package com.librarymanager.model;

import java.util.Date;

public class BookingInfo {
	private int booking_id;
	private int user_id;
	private int book_id;
	private Date start_time;
	private int close_state;
	
	public int getClose_state() {
		return close_state;
	}
	public void setClose_state(int close_state) {
		this.close_state = close_state;
	}
	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
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
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

}
