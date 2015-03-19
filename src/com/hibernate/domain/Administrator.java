package com.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Administrator entity. @author MyEclipse Persistence Tools
 */


public class Administrator implements java.io.Serializable {

	// Fields
	

	private Integer id;
	private String name;
	private Integer phonenumber;
	private String qq;
	private String mail;
	private String address;

	private Set informs = new HashSet(0);
	private String password;
	// Constructors

	/** default constructor */
	public Administrator() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/** minimal constructor */
	public Administrator(String name, Integer phonenumber, String mail) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.mail = mail;
	}

	/** full constructor */
	public Administrator(String name, Integer phonenumber, String qq,
			String mail, String address, Set informs) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.qq = qq;
		this.mail = mail;
		this.address = address;
		;
		this.informs = informs;
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

	public Integer getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(Integer phonenumber) {
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

	

	public Set getInforms() {
		return this.informs;
	}

	public void setInforms(Set informs) {
		this.informs = informs;
	}

}