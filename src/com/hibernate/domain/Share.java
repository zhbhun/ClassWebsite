package com.hibernate.domain;

import java.sql.Timestamp;

/**
 * Share entity. @author MyEclipse Persistence Tools
 */

public class Share implements java.io.Serializable {

	
	
	// Fields

	private Integer id;
	private Student student;
	private String name;
	private String content;
	private String attachment;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Share() {
	}

	/** minimal constructor */
	public Share(Student student, String name, String content,
			Timestamp time) {
		this.student = student;
		this.name = name;
		this.content = content;
		this.time = time;
	}

	/** full constructor */
	public Share(Student student, String name, String content,
			String attachment, Timestamp time) {
		this.student = student;
		this.name = name;
		this.content = content;
		this.attachment = attachment;
		this.time = time;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAttachment() {
		return this.attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}