package com.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.Page;
import com.hibernate.domain.Link;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.LinkBean;

public class LinkAction extends ActionSupport {
  private LinkBean linkBean;

  private Link link;
  private Integer linkId;
  
  private List<Link> linklist;
  private Page page;
  private String operationState;
  private boolean isShowTip;
   //zhb*****************************************
  //get links
  public String getLinkForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(page==null){
		  page = new Page();
		  page.setCurrentPage(1);
	  }
	  linklist = linkBean.getLinkForAdmin(page);
	  if(linklist==null)
		  linklist = new ArrayList<Link>();
	  ActionContext.getContext().getSession().put("linkPage", page);
	  return "admin_linklist";
}
//·­Ò³
  public String changeLinkPage(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  int currentPage ;
	 if(page == null)
			currentPage = 1;
	  else
			currentPage = page.getCurrentPage();
	 page = (Page)ActionContext.getContext().getSession().get("linkPage");
	 if(page == null)
		 page =  new Page(); 
	 page.setCurrentPage(currentPage);
	 linklist = linkBean.getLinkForAdmin(page);
	  if(linklist==null)
		  linklist = new ArrayList<Link>();
	  ActionContext.getContext().getSession().put("linkPage", page);
	  return "admin_linklist";
  }
//add
  public String addLink(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(link==null){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  if(link.getName().equals("")){
		  setShowTip(true);
		  setOperationState("error:404");
		  return "add_result";
	  }
	  boolean isSuccess = linkBean.addLink(link);
	  if(isSuccess){
		  setShowTip(true);
		  setOperationState("error:404");
	  }
	  return "add_result";
	  
}
//access change
  public void accessChangeLink(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  System.out.println(linkId);
	  if(linkId==null)
		  return;
	  link = linkBean.getLinkById(linkId);
	  if(link==null)
		  return;
	  String json = "";
		json += "{\"id\":\""+link.getId()+"\","; 
		if(link.getName()!=null)
			json += "\"name\":\""+link.getName().replace("\"", "\\\"")+"\",";
			else
				json += "\"name\":\"\",";
		if(link.getAddress()!=null)
		       json +="\"address\":\""+link.getAddress().replace("\"", "\\\"")+"\"}";
		else
			json +="\"address\":\"\"}";
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
  public String changeLink(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  page = (Page)ActionContext.getContext().getSession().get("linkPage");
	  if(page==null){
		  page = new Page();
		 page.setCurrentPage(1);  
	  }
	  if(link==null)
		  return "change_result";
	  boolean isSuccess = linkBean.changeLink(link);
	  return "change_result";
	 
	 
  } 
  //É¾³ý
  public String deleteLink(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  page = (Page)ActionContext.getContext().getSession().get("linkPage");
	  if(page==null){
		  page = new Page();
		 page.setCurrentPage(1);  
	  }
	  if(linkId==null)
		  return "delete_result";
	  boolean isSuccess = linkBean.deleteLink(linkId);
	  return "delete_result";
  }
public LinkBean getLinkBean() {
	return linkBean;
}

public void setLinkBean(LinkBean linkBean) {
	this.linkBean = linkBean;
}
public Link getLink() {
	return link;
}
public void setLink(Link link) {
	this.link = link;
}
public Integer getLinkId() {
	return linkId;
}
public void setLinkId(Integer linkId) {
	this.linkId = linkId;
}
public List<Link> getLinklist() {
	return linklist;
}
public void setLinklist(List<Link> linklist) {
	this.linklist = linklist;
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
