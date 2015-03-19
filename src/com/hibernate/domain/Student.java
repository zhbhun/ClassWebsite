package com.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	
	
	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Job job;
	private Banji banji;
	private String studentNum;
	private String name;
	private String password;
	private Integer age;
	private String sex;
	private String phonenumber;
	private String qq;
	private String mail;
	private String address;
	private String nativePalce;
	private String introduction;
	private Set shares = new HashSet(0);
	public Set getShares() {
		return shares;
	}

	public void setShares(Set shares) {
		this.shares = shares;
	}

	private Set informComments = new HashSet(0);
	private Set photoComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** minimal constructor */
	public Student(Job job, Banji banji, String studentNum, String name,
			Integer age, String sex, String phonenumber,  Set shares) {
		this.job = job;
		this.banji = banji;
		this.studentNum = studentNum;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.shares = shares;
		this.phonenumber = phonenumber;
	}

	/** full constructor */
	public Student(Job job, Banji banji, String studentNum, String name,
			Integer age, String sex, String phonenumber, String qq,
			String mail, String address, String nativePalce,
			String introduction, Set informComments,
			Set photoComments) {
		this.job = job;
		this.banji = banji;
		this.studentNum = studentNum;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.phonenumber = phonenumber;
		this.qq = qq;
		this.mail = mail;
		this.address = address;
		this.nativePalce = nativePalce;
		this.introduction = introduction;
		this.informComments = informComments;
	
		this.photoComments = photoComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Job getJob() {
		return this.job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Banji getBanji() {
		return this.banji;
	}

	public void setBanji(Banji banji) {
		this.banji = banji;
	}

	public String getStudentNum() {
		return this.studentNum;
	}

	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNativePalce() {
		return this.nativePalce;
	}

	public void setNativePalce(String nativePalce) {
		this.nativePalce = nativePalce;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Set getInformComments() {
		return this.informComments;
	}

	public void setInformComments(Set informComments) {
		this.informComments = informComments;
	}

	

	public Set getPhotoComments() {
		return this.photoComments;
	}

	public void setPhotoComments(Set photoComments) {
		this.photoComments = photoComments;
	}

}