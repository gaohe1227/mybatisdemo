package com.demo.model;

import java.io.Serializable;

public class Teacher implements Serializable{
	 

	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	private int t_id;
	private String t_name;
	 
	 
}
