package com.hibernate.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Inform entity. @author MyEclipse Persistence Tools
 */

public class Inform implements java.io.Serializable {

	// Fields

	
	private Integer id;
	private Administrator administrator;
	private String name;
	private String content;
	private Timestamp time;
	private Set informComments = new HashSet(0);

	// Constructors

	/** default constructor */
	public Inform() {
	}

	/** minimal constructor */
	public Inform(Administrator administrator, String name, String content,
			Timestamp time) {
		this.administrator = administrator;
		this.name = name;
		this.content = content;
		this.time = time;
	}

	/** full constructor */
	public Inform(Administrator administrator, String name, String content,
			Timestamp time, Set informComments) {
		this.administrator = administrator;
		this.name = name;
		this.content = content;
		this.time = time;
		this.informComments = informComments;
	}

	// Property accessors
    
	public Integer getId() {
		return this.id;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+"\n|"+id+"|"+name+"|"+content+"|"+time+"|"+administrator+"|";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Administrator getAdministrator() {
		return this.administrator;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Set getInformComments() {
		return this.informComments;
	}

	public void setInformComments(Set informComments) {
		this.informComments = informComments;
	}
   
}