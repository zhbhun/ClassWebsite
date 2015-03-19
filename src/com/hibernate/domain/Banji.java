package com.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Banji entity. @author MyEclipse Persistence Tools
 */

public class Banji implements java.io.Serializable {

	
	// Fields

	private Integer id;
	private Integer classNum;
	private String grade;
	private String major;
	private String school;
	private String indroduct;
	private Set students = new HashSet(0);

	// Constructors

	/** default constructor */
	public Banji() {
	}

	/** minimal constructor */
	public Banji(Integer classNum, String grade, String major, String school) {
		this.classNum = classNum;
		this.grade = grade;
		this.major = major;
		this.school = school;
	}

	/** full constructor */
	public Banji(Integer classNum, String grade, String major, String school,
			String indroduct, Set students) {
		this.classNum = classNum;
		this.grade = grade;
		this.major = major;
		this.school = school;
		this.indroduct = indroduct;
		this.students = students;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClassNum() {
		return this.classNum;
	}

	public void setClassNum(Integer classNum) {
		this.classNum = classNum;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSchool() {
		return this.school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getIndroduct() {
		return this.indroduct;
	}

	public void setIndroduct(String indroduct) {
		this.indroduct = indroduct;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

}