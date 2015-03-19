package com.hibernate.domain;

/**
 * PhotoComment entity. @author MyEclipse Persistence Tools
 */

public class PhotoComment implements java.io.Serializable {

	// Fields
	

	private Integer id;
	private Photo photo;
	private Student student;
	private String time;
	private String context;

	// Constructors

	/** default constructor */
	public PhotoComment() {
	}

	/** minimal constructor */
	public PhotoComment(Student student) {
		this.student = student;
	}

	/** full constructor */
	public PhotoComment(Photo photo, Student student, String time,
			String context) {
		this.photo = photo;
		this.student = student;
		this.time = time;
		this.context = context;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return this.photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContext() {
		return this.context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}