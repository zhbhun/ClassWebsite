package com.hibernate.domain;

import java.sql.Timestamp;

/**
 * InformComment entity. @author MyEclipse Persistence Tools
 */

public class InformComment implements java.io.Serializable {

	// Fields

	
	private Integer id;
	private Student student;
	private Inform inform;
	private Timestamp time;
	private String content;

	// Constructors

	/** default constructor */
	public InformComment() {
	}

	/** full constructor */
	public InformComment(Student student, Inform inform, Timestamp time,
			String content) {
		this.student = student;
		this.inform = inform;
		this.time = time;
		this.content = content;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Inform getInform() {
		return this.inform;
	}

	public void setInform(Inform inform) {
		this.inform = inform;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}