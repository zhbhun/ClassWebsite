package com.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.AdministratorBean;
  


public class AdministratorAction extends ActionSupport {
  private AdministratorBean administratorBean;

public AdministratorBean getAdministratorBean() {
	return administratorBean;
}

public void setAdministratorBean(AdministratorBean administratorBean) {
	this.administratorBean = administratorBean;
}
  
}
