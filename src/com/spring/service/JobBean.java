package com.spring.service;

import java.util.List;

import com.hibernate.dao.JobDAO;
import com.hibernate.domain.Job;

public class JobBean {
   private JobDAO jobDAO;

public JobDAO getJobDAO() {
	return jobDAO;
}

public void setJobDAO(JobDAO jobDAO) {
	this.jobDAO = jobDAO;
}
   public Job findById(Integer id){
	   Job job = new Job();
	   try{
		    job =  jobDAO.findById(id);
	   }catch(Exception e){
		   job.setName("Ñ§Éú");
	   }
	   return job;
   }
   //zhb
	//get All job
  public List<Job> getAll(){
	  return jobDAO.findAll();
	  
  }
	//*****************************
}
