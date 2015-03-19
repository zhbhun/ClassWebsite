package com.common;

public class StudentPage extends Page {
   private boolean isSerchByJob;
   private Integer jobId;
public boolean isSerchByJob() {
	return isSerchByJob;
}

public void setSerchByJob(boolean isSerchByJob) {
	this.isSerchByJob = isSerchByJob;
}
public Integer getJobId() {
	return jobId;
}
public void setJobId(Integer jobId) {
	if(jobId != 0)
		isSerchByJob = true;
	this.jobId = jobId;
}
   
}
