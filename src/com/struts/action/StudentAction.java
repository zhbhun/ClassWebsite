package com.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.StudentPage;
import com.hibernate.domain.Banji;
import com.hibernate.domain.Job;
import com.hibernate.domain.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.BanjiBean;
import com.spring.service.JobBean;
import com.spring.service.StudentBean;

public class StudentAction extends ActionSupport {

	private StudentBean studentBean;
	private JobBean jobBean;
	private BanjiBean banjiBean;
	
	private Student student;
	private Integer studentId;
	private Integer jobId;
	
	 private List<Student> studentList;
	 private List<Job> jobList;
	private StudentPage page;
	private String operationState;
    private boolean isShowTip;
	
	 //zhb*****************************************
    //get student
    
    public String getStudentForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(page==null){
		  page = new StudentPage();
		  page.setCurrentPage(1);
	  }
	  jobList = jobBean.getAll();
	  studentList = studentBean.getStudentForAdmin(page);
	  if(studentList==null)
		  studentList = new ArrayList<Student>();
	  ActionContext.getContext().getSession().put("studentPage", page);
	  return "admin_studentlist";
}
//·­Ò³
  public String changeStudentPage(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  int currentPage ;
	 if(page == null)
			currentPage = 1;
	  else
			currentPage = page.getCurrentPage();
	 page = (StudentPage)ActionContext.getContext().getSession().get("studentPage");
	 if(page == null)
		 page =  new StudentPage(); 
	 page.setCurrentPage(currentPage);
	  jobList = jobBean.getAll();
	 studentList = studentBean.getStudentForAdmin(page);
	  if(studentList==null)
		  studentList = new ArrayList<Student>();
	  ActionContext.getContext().getSession().put("studentPage", page);
	  return "admin_studentlist";
  }
//add
  public String addStudent(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(student==null){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  if(student.getName().equals("")){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  boolean isSuccess = studentBean.addStudent(student);
	  if(isSuccess){
		  setShowTip(true);
		  setOperationState("error:404");
	  }
	  return "add_result";
	  
} 
 //get  student by id
  public String getStudentById(){
	  if(studentId==null){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "someome_student";
	  }
	student = studentBean.getStudentById(studentId);
	if(student==null){
		  setShowTip(true);
		  setOperationState("error:404");
		 
	  }
	 return "someome_student";
}
  //get student by id for json
  public void getStudentByIdToJson(){
	  System.out.println(studentId);
	  if(studentId==null){
		  setShowTip(true);
		  setOperationState("error:404");
		  return;
	  }
	student = studentBean.getStudentById(studentId);
	if(student==null){
		  setShowTip(true);
		  setOperationState("error:404");
		 return;
	  }
	 String json = "";
		json += "{\"studentNum\":\""+student.getStudentNum()+"\","; 
		json += "\"name\":\""+student.getName()+"\",";
		if(student.getAge()!=null)
		   json += "\"age\":\""+student.getAge()+"\",";
		else
			 json += "\"age\":\"\",";
		json += "\"sex\":\""+student.getSex()+"\",";
		if(student.getPhonenumber()!=null)
		   json += "\"phone\":\""+student.getPhonenumber()+"\",";
		else
			 json += "\"phone\":\"\",";
		if(student.getQq()!=null)
		   json += "\"qq\":\""+student.getQq()+"\",";
		else
			 json += "\"qq\":\"\",";
		if(student.getMail()!=null)
		   json += "\"mail\":\""+student.getMail()+"\",";
		else
			 json += "\"mail\":\"\",";
		if(student.getAddress()!=null)
		   json += "\"address\":\""+student.getAddress()+"\",";
		else
			   json += "\"address\":\"\",";
			
		json += "\"job\":\""+student.getJob().getName()+"\"}";
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
 //change
  public void changeJob(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}

	  System.out.println(studentId+"|"+jobId);
	  HttpServletResponse response = ServletActionContext.getResponse();
	    response.setContentType("application/json;charset=UTF-8");  
		  response.setHeader("Charset","UTF-8"); 
		        PrintWriter out = null;
	  String json = "{\"state\":\"";
	  if(studentId==null || jobId==null){
		  json += "fail\"}";
	  }
	  boolean isSuccess = studentBean.changeStudentJob(studentId,jobId);
	  if(isSuccess)
		  json +="success\"}";
	  else
		  json += "fail\"}";
	  try {
  		   out = response.getWriter();
  		  } catch (IOException e) {
  		   // TODO Auto-generated catch block
  		   e.printStackTrace();
  		  }
  out.write(json);
 out.flush();  
  }
  //delete
  public String deleteStudent(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  page = (StudentPage)ActionContext.getContext().getSession().get("studentPage");
	  if(page==null){
		  page = new StudentPage();
		 page.setCurrentPage(1);  
	  }
	  if(studentId==null)
		  return "delete_result";
	  boolean isSuccess = studentBean.deleteStudent(studentId);
	  return "delete_result";
  }
	
	
    public BanjiBean getBanjiBean() {
		return banjiBean;
	}

	public void setBanjiBean(BanjiBean banjiBean) {
		this.banjiBean = banjiBean;
	}

	public JobBean getJobBean() {
		return jobBean;
	}

	public void setJobBean(JobBean jobBean) {
		this.jobBean = jobBean;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	private List<Student> cards;
    private List<Student> leader;
	public List<Student> getLeader() {
		return leader;
	}

	public void setLeader(List<Student> leader) {
		this.leader = leader;
	}

	public List<Student> getCards() {
		return cards;
	}

	public void setCards(List<Student> cards) {
		this.cards = cards;
	}

	public StudentBean getStudentBean() {
		return studentBean;
	}

	public void setStudentBean(StudentBean studentBean) {
		this.studentBean = studentBean;
	}
	

	public String leader(){
	leader = studentBean.findLeader();
		return "leader";
	}
	public String cards(){
		cards = studentBean.findAll();
		return "cards";
	}
	public String login(){
		try{
		    student = studentBean.findByStudentNum(student.getStudentNum());
			ActionContext.getContext().getSession().put("student", student);
			return "inform";
		}catch(Exception e){
			ActionContext.getContext().getSession().put("error", "µÇÂ¼´íÎó");
			return "error";
		}
		
	}
	public String modify(){
		try{
			Job job = jobBean.findById(student.getJob().getId());
			student.setJob(job);
			Banji banji = banjiBean.findById(student.getBanji().getId());
			student.setBanji(banji);
			studentBean.update(student);
			return "modify";
		}catch(Exception e){
			ActionContext.getContext().getSession().put("error", "ÐÞ¸ÄÐÅÏ¢Ê§°Ü");
			return "error";
		}
	}
	public String inform(){
		try{
		student = (Student)ActionContext.getContext().getSession().get("student");
		student = studentBean.findByStudentNum(student.getStudentNum());
		return "inform";
		}catch(Exception e){
			ActionContext.getContext().getSession().put("error", "ÇëÏÈµÇÂ¼");
			return "error";
		}
	}
	public String logout(){
		ActionContext.getContext().getSession().put("student", null);
		return "main";
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public List<Job> getJobList() {
		return jobList;
	}
	public void setJobList(List<Job> jobList) {
		this.jobList = jobList;
	}
	public StudentPage getPage() {
		return page;
	}
	public void setPage(StudentPage page) {
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
