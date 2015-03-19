package com.struts.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hibernate.domain.Inform;
import com.hibernate.domain.InformComment;
import com.hibernate.domain.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.InformBean;
import com.spring.service.InformCommentBean;

public class InformCommentAction extends ActionSupport {
  private InformCommentBean informCommentBean;
  private InformComment informComment;
  private Inform inform;
  private InformBean informBean;
  private List list;
   private Integer informId;
  private Integer informCommentId;
  private List<InformComment> informCommentList;
  private String operationState;
  private boolean isShowTip;
  
  
  //�Ż���****************************************************************
//֪ͨ���۹���
  public String getInformCommentByInformId(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		} 
	 System.out.println(informId);
	  if(informId==null){
		  setShowTip(true);
		  setOperationState("������");
		  informCommentList = new ArrayList<InformComment>();
		  return "informCommentForAdmin";
	  }
	  informCommentList = informCommentBean.getInformCommentByInformId(informId);
	  if(informCommentList.size()>0)
		  setOperationState("��֪ͨ��"+informCommentList.size()+"������");
	  else
		  setOperationState("û�������۸�֪ͨ");
	  return "informCommentForAdmin";
  }
 //ɾ������
  public String deleteInformComment(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		} 
	  if(informCommentId==null){
		  setOperationState("������,û��ɾ����Ҫɾ����֪ͨ");
		  setShowTip(true);
		  informCommentList = new ArrayList<InformComment>();
		  return "informCommentForAdmin"; 
	  }
	  informCommentList = informCommentBean.deleteInformComment(informCommentId);
	  if(informCommentList.size()>0)
		  setOperationState("ɾ���ɹ���Ŀǰ��֪ͨ��"+informCommentList.size()+"������");
	  else
		  setOperationState("ɾ���ɹ���Ŀǰû�������۸�֪ͨ");
	  return "informCommentForAdmin"; 
  }
  //*********************************************
  
public List getList() {
	return list;
}

public void setList(List list) {
	this.list = list;
}

public InformBean getInformBean() {
	return informBean;
}

public void setInformBean(InformBean informBean) {
	this.informBean = informBean;
}

public Inform getInform() {
	return inform;
}

public void setInform(Inform inform) {
	this.inform = inform;
}

public InformComment getInformComment() {
	return informComment;
}

public void setInformComment(InformComment informComment) {
	this.informComment = informComment;
}

public InformCommentBean getInformCommentBean() {
	return informCommentBean;
}

public void setInformCommentBean(InformCommentBean informCommentBean) {
	this.informCommentBean = informCommentBean;
}

public String informAdd(){
	
	return "informAdd";
}
public String comment(){
	try{
		String content = informComment.getContent();
		Integer informCommentId = informComment.getInform().getId();
		Inform i = informBean.findById(informCommentId);
		System.out.println(i);
		Student student = (Student)ActionContext.getContext().getSession().get("student");
		inform = informCommentBean.comment(content, i, student).getInform();
		Set<InformComment> set = inform.getInformComments();
		 list = new ArrayList();
	    for(InformComment l:set){
	    	list.add(l);
	    }
		return "comment";
	}catch(Exception e){
		ActionContext.getContext().getSession().put("error", "������Ӵ���");
		return "error";
	}
}
public Integer getInformId() {
	return informId;
}
public void setInformId(Integer informId) {
	this.informId = informId;
}
public Integer getInformCommentId() {
	return informCommentId;
}
public void setInformCommentId(Integer informCommentId) {
	this.informCommentId = informCommentId;
}
public List<InformComment> getInformCommentList() {
	return informCommentList;
}
public void setInformCommentList(List<InformComment> informCommentList) {
	this.informCommentList = informCommentList;
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
