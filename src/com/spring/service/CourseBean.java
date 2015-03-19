package com.spring.service;

import java.util.List;

import com.common.Page;
import com.hibernate.dao.CourseDAO;
import com.hibernate.domain.Course;

public class CourseBean {
  private CourseDAO courseDAO;

public CourseDAO getCourseDAO() {
	return courseDAO;
}

public void setCourseDAO(CourseDAO courseDAO) {
	this.courseDAO = courseDAO;
}
  //zhb*****************************************
//»ñÈ¡¿Î³Ì
public List<Course> getCoursesForAdmin(Page page){
	List<Course> list;
	page.setPageSize(10);
	 page.setSearchByOrderOfTime(false);
	 list = courseDAO.getCoursesForAdmin(page);
		if(page.getCurrentPage()==page.getTotalPage() || page.getTotalPage() == 0)
	    	page.setHasNextPage(false);
	    else
	    	page.setHasNextPage(true);
	    if(page.getCurrentPage()==1)
	    	page.setHasPrePage(false);
	    else
	    	page.setHasPrePage(true);
	    System.out.println(page.toString());
		return list;
}
//add
public boolean addCourse(Course course){
	
	return courseDAO.addCourse(course);
}
//getByid
public Course getCourseById(int id){
	return courseDAO.getCourseById(id);
}
//delete
public boolean deleteCourse(int id){
	return courseDAO.deleteCourse(id);
}
//change
public boolean changeCourse(Course course){
	return courseDAO.changeCourse(course);
}
//*************************
}
