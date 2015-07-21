package com.librarymanager.model;

public class FineInfo {
	private int fine_id;
	private int user_id;
	private String fine_reason;
	private int fine_amount;
	private int book_id;
	public int getFine_id() {
		return fine_id;
	}
	public void setFine_id(int fine_id) {
		this.fine_id = fine_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getFine_reason() {
		return fine_reason;
	}
	public void setFine_reason(String fine_reason) {
		this.fine_reason = fine_reason;
	}
	public int getFine_amount() {
		return fine_amount;
	}
	public void setFine_amount(int fine_amount) {
		this.fine_amount = fine_amount;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	
}
