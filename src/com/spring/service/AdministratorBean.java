package com.spring.service;

import com.hibernate.dao.AdministratorDAO;


public class AdministratorBean {
  private AdministratorDAO administratorDAO;

public AdministratorDAO getAdministratorDAO() {
	return administratorDAO;
}

public void setAdministratorDAO(AdministratorDAO administratorDAO) {
	this.administratorDAO = administratorDAO;
}
  
}
