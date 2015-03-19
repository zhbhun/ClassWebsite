package com.hibernate.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Album entity. @author MyEclipse Persistence Tools
 */

public class Album implements java.io.Serializable {

	
	
	
	// Fields

	private Integer id;
	private Administrator administrator;
	private String name;
	private Timestamp time;
	private String introduction;
	private Set photos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Album() {
	}

	/** minimal constructor */
	public Album(Administrator administrator) {
		this.administrator = administrator;
	}

	/** full constructor */
	public Album(Administrator administrator, String name, Timestamp time,
			String introduction, Set photos) {
		this.administrator = administrator;
		this.name = name;
		this.time = time;
		this.introduction = introduction;
		this.photos = photos;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public Set getPhotos() {
		return this.photos;
	}

	public void setPhotos(Set photos) {
		this.photos = photos;
	}

}