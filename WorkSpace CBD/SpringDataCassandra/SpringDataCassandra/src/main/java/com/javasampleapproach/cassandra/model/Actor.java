package com.javasampleapproach.cassandra.model;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
public class Actor {
	
	@PrimaryKey
	private int id;
	private String fullname;
	private int year;
	private int age;
	private String movie;
	
	public Actor(){}

	
	
	


	public Actor(int id, String fullName, int year, int age, String movie) {
		super();
		this.id = id;
		this.fullname = fullName;
		this.year = year;
		this.age = age;
		this.movie = movie;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullName) {
		this.fullname = fullName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}






	public int getAge() {
		return age;
	}






	public void setAge(int age) {
		this.age = age;
	}






	@Override
	public String toString() {
		return "Actor [id=" + id + ", name=" + fullname + ", year=" + year + ", age=" + age + ", movie=" + movie
				+ "]";
	}


	
	
	
	
	
	 

	
	

}
