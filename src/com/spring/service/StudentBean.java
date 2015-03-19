package com.spring.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.common.StudentPage;
import com.hibernate.dao.StudentDAO;
import com.hibernate.domain.Student;

public class StudentBean {
	StudentDAO studentDAO;

	public StudentDAO getStudentDAO() {
		return studentDAO;
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}
	public List<Student> findLeader(){
		List<Student> students = new ArrayList<Student>();
		List<Student> student = studentDAO.findAll();
		Iterator<Student> iter = student.iterator() ; 
		while(iter.hasNext()) { 
			Student s = (Student)iter.next();
			System.out.println(s.getJob().getName());
			if(!s.getJob().getName().trim().equals("Ñ§Éú")){
				students.add(s);
			}
		}
		return students;
	}
	public  List<Student> findAll(){
		List<Student> students = new ArrayList<Student>();
		students = studentDAO.findAll();
		return students;
	}
	public Student findByStudentNum(String studentNum){
		List list = studentDAO.findByStudentNum(studentNum);
		Student student = (Student) list.get(0);
		return student;
	}
	public void update(Student student){
		studentDAO.attachDirty(student);
		//studentDAO.f
	}
	//zhb*****************************************
	//get student
	public List<Student> getStudentForAdmin(StudentPage page){
		List<Student> list;
		page.setPageSize(9);
		 page.setSearchByOrderOfTime(false);
		 list = studentDAO.getStudentForAdmin(page);
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
	public boolean addStudent(Student student){
		
		return studentDAO.addStudent(student);
	}
    // get student by id
	 public Student getStudentById(int id){
		 return studentDAO.getStudentById(id);
	 }
   //change
	 public boolean changeStudentJob(int studentId,int jobId){
		 return studentDAO.changeStudentJob(studentId,jobId);
	 }
   //delete
	 public boolean deleteStudent(int id){
		 return studentDAO.deleteStudent(id);
	 }
	 //*************************************************
}
