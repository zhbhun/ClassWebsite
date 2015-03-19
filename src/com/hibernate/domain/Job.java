package com.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Job entity. @author MyEclipse Persistence Tools
 */

public class Job implements java.io.Serializable {

	// Fields

	
	
	private Integer id;
	private String name;
	private String description;
	private Set students = new HashSet(0);

	// Constructors

	/** default constructor */
	public Job() {
	}

	/** minimal constructor */
	public Job(String name) {
		this.name = name;
	}

	/** full constructor */
	public Job(String name, String description, Set students) {
		this.name = name;
		this.description = description;
		this.students = students;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

}