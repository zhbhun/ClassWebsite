package com.hibernate.domain;

/**
 * Link entity. @author MyEclipse Persistence Tools
 */

public class Link implements java.io.Serializable {

	// Fields

	
	private Integer id;
	private String name;
	private String address;

	// Constructors

	/** default constructor */
	public Link() {
	}

	/** full constructor */
	public Link(String name, String address) {
		this.name = name;
		this.address = address;
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

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}