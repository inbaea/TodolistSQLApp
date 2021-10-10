package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	private int id;
    private String title;
    private String memo;
    private String current_date;
    private String category;
    private String due_date;
    private int is_completed;
    
    public TodoItem(String category, String title, String memo, String due_date, int is_completed){
    	this.category=category;
        this.title=title;
        this.memo=memo;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.due_date=due_date;
        this.is_completed=is_completed;
    }
    public TodoItem(String category, String title, String memo, String due_date){
    	this.category=category;
        this.title=title;
        this.memo=memo;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
        this.due_date=due_date;
    }
    
	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDue_date() {
		return due_date;
	}
	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	@Override
	public String toString() {
		if(is_completed == 1)
			return  id + "." + " [" + category  + "] "+ title + "[V]" + " - " + memo + " - " + due_date + " - " + current_date;
		else
			return  id + "." + " [" + category  + "] "+ title + " - " + memo + " - " + due_date + " - " + current_date;
	}

}
