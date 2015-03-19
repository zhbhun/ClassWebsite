package com.hibernate.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Photo entity. @author MyEclipse Persistence Tools
 */

public class Photo implements java.io.Serializable {

	// Fields
	

	private Integer id;
	private Album album;
	private String name;
	private Timestamp time;
	private String introduction;
	private String content;
	private Set photoComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Photo() {
	}

	/** minimal constructor */
	public Photo(String name, Timestamp time, String content) {
		this.name = name;
		this.time = time;
		this.content = content;
	}

	/** full constructor */
	public Photo(Album album, String name, Timestamp time, String introduction,
			String content, Set photoComments) {
		this.album = album;
		this.name = name;
		this.time = time;
		this.introduction = introduction;
		this.content = content;
		this.photoComments = photoComments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set getPhotoComments() {
		return this.photoComments;
	}

	public void setPhotoComments(Set photoComments) {
		this.photoComments = photoComments;
	}

}