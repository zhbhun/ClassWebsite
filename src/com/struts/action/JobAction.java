package com.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.JobBean;

public class JobAction extends ActionSupport{
  private JobBean jobBean;

public JobBean getJobBean() {
	return jobBean;
}

public void setJobBean(JobBean jobBean) {
	this.jobBean = jobBean;
}
  
}
