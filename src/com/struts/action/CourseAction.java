package com.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.Page;
import com.hibernate.domain.Course;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.CourseBean;

public class CourseAction extends ActionSupport {
  private CourseBean courseBean;

  private Course course;
  private Integer courseId;
  
  private List<Course> courseList;
  private Page page;
  private String operationState;
  private boolean isShowTip;
   //zhb*****************************************
  //获取课程
  public String getCoursesForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(page==null){
		  page = new Page();
		  page.setCurrentPage(1);
	  }
	  courseList = courseBean.getCoursesForAdmin(page);
	  if(courseList==null)
		  courseList = new ArrayList<Course>();
	  ActionContext.getContext().getSession().put("coursePage", page);
	  return "admin_courselist";
  }
//翻页查看课程表
  public String changeCoursesPage(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  int currentPage ;
	 if(page == null)
			currentPage = 1;
	  else
			currentPage = page.getCurrentPage();
	 page = (Page)ActionContext.getContext().getSession().get("coursePage");
	 if(page == null)
		 page =  new Page(); 
	 page.setCurrentPage(currentPage);
	 courseList = courseBean.getCoursesForAdmin(page);
	  if(courseList==null)
		  courseList = new ArrayList<Course>();
	  ActionContext.getContext().getSession().put("coursePage", page);
	  return "admin_courselist";
  }
//添加课程
  public String addCourse(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(course==null){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  if(course.getName().equals("")){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  boolean isSuccess = courseBean.addCourse(course);
	  if(isSuccess){
		  setShowTip(true);
		  setOperationState("error:404");
	  }
	  return "add_result";
	  
  }
  //获取待修改课程信息
  public void accessChangeCourse(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  System.out.println(courseId);
	  if(courseId==null)
		  return;
	  course = courseBean.getCourseById(courseId);
	  if(course==null)
		  return;
	  String json = "";
		json += "{\"id\":\""+course.getId()+"\","; 
		if(course.getName()!=null)
			json += "\"name\":\""+course.getName().replace("\"", "\\\"")+"\",";
			else
				json += "\"name\":\"\",";
		if(course.getTime()!=null)
		json += "\"time\":\""+course.getTime().replace("\"", "\\\"")+"\",";
		else
			json += "\"time\":\"\",";
		if(course.getTeacher()!=null)
		   json += "\"teacher\":\""+course.getTeacher().replace("\"", "\\\"")+"\",";
		else
			json += "\"teacher\":\"\",";
		if(course.getPlace()!=null)
			   json += "\"place\":\""+course.getPlace().replace("\"", "\\\"")+"\",";
		else
				json += "\"place\":\"\",";
		if(course.getTelephone()!=null)
			   json += "\"telephone\":\""+course.getTelephone().replace("\"", "\\\"")+"\",";
		else
				json += "\"telephone\":\"\",";
		if(course.getMail()!=null)
			   json += "\"mail\":\""+course.getMail().replace("\"", "\\\"")+"\",";
		else
				json += "\"mail\":\"\",";
		if(course.getOther()!=null)
		       json +="\"other\":\""+course.getOther().replace("\"", "\\\"")+"\"}";
		else
			json +="\"other\":\"\"}";
		HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json;charset=UTF-8");  
		  response.setHeader("Charset","UTF-8"); 
		        PrintWriter out = null;
		        try {
		   		   out = response.getWriter();
		   		  } catch (IOException e) {
		   		   // TODO Auto-generated catch block
		   		   e.printStackTrace();
		   		  }
		   out.write(json);
		  out.flush();  
  }
  //修改课程
  public String changeCourse(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  page = (Page)ActionContext.getContext().getSession().get("coursePage");
	  if(page==null){
		  page = new Page();
		 page.setCurrentPage(1);  
	  }
	  if(course==null){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  if(course.getName().equals("")){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  boolean isSuccess = courseBean.changeCourse(course);
	  return "change_result";
	  
  }
  //删除课程
  public String deleteCourse(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  page = (Page)ActionContext.getContext().getSession().get("coursePage");
	  if(page==null){
		  page = new Page();
		 page.setCurrentPage(1);  
	  }
	  if(courseId==null)
		  return "delete_result";
	  boolean isSuccess = courseBean.deleteCourse(courseId);
	  return "delete_result";
  }
  //*****************************************************
public CourseBean getCourseBean() {
	return courseBean;
}

public void setCourseBean(CourseBean courseBean) {
	this.courseBean = courseBean;
}
public Course getCourse() {
	return course;
}
public void setCourse(Course course) {
	this.course = course;
}
public Integer getCourseId() {
	return courseId;
}
public void setCourseId(Integer courseId) {
	this.courseId = courseId;
}
public List<Course> getCourseList() {
	return courseList;
}
public void setCourseList(List<Course> courseList) {
	this.courseList = courseList;
}
public Page getPage() {
	return page;
}
public void setPage(Page page) {
	this.page = page;
}
public String getOperationState() {
	return operationState;
}
public void setOperationState(String operationState) {
	this.operationState = operationState;
}
public boolean isShowTip() {
	return isShowTip;
}
public void setShowTip(boolean isShowTip) {
	this.isShowTip = isShowTip;
}
  
}
