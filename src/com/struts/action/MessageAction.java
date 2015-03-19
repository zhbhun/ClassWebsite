package com.struts.action;

import java.util.ArrayList;
import java.util.List;

import com.common.Page;
import com.hibernate.domain.Message;
import com.hibernate.domain.Student;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.MessageBean;

public class MessageAction extends ActionSupport {
  private MessageBean messageBean;
  private Message message;
   private Integer messageId;
  
  private List<Message> messageList;
  private String operationState;
	private Page page;
	private boolean isShowTip;
  //�Ż���****************************************************************
//֪ͨ����
	//��ҳ��ȡ����
	public String getMessageForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(page!=null){
			messageList = messageBean.getMessageForAdmin(page);
			if(messageList!=null && messageList.size()> 0)
			      setOperationState("��"+messageList.size()+"����");
			    else 
			      setOperationState("��ʱû��������");
		}
		else{
			setShowTip(true);
			setOperationState("��ǰ����ҳ���д������·���");
			messageList = new ArrayList<Message>();
			
		}
		ActionContext.getContext().getSession().put("messagePage", page);
		return "admin_messagelist";
	}
	//��ҳ�鿴����
	public String changeMessagePage(){
		if(page==null){
			setShowTip(true);
			setOperationState("��ǰ����ҳ���д������·���");
			messageList = new ArrayList<Message>();
			return "admin_messagelist";
		}
		int currentPage = page.getCurrentPage();
		page = (Page)ActionContext.getContext().getSession().get("messagePage");
		if(page!=null){
			 System.out.println(page);
			 page.setCurrentPage(currentPage);
			 messageList = messageBean.getMessageForAdmin(page);
			 if(messageList!=null && messageList.size()> 0)
			      System.out.println(messageList.size());
			  else 
			      setOperationState("û������");
		}
		else{
			page = new Page();
			page.setCurrentPage(1);
			 messageList = messageBean.getMessageForAdmin(page);
			 if(messageList!=null && messageList.size()> 0)
			      System.out.println(messageList.size());
			  else 
			      setOperationState("û������");
		}
		ActionContext.getContext().getSession().put("messagePage", page);
		return "admin_messagelist";
	}
//ɾ������
	public String deleteMessageForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		} 
		 if(messageId==null){
			  setOperationState("������,û��ɾ����Ҫɾ��������");
			  return "delete_result";
		 }
		 boolean isSuccess = messageBean.deleteMessageForAdmin(messageId);
		 if(isSuccess)
			setOperationState("ɾ���ɹ�");
		 else
			setOperationState("������,û��ɾ����Ҫɾ��������");
		 
		 return "delete_result";
	}
	//**************************************************
  public Message getMessage() {
	return message;
}

public void setMessage(Message message) {
	this.message = message;
}

public List getMessageList() {
	return messageList;
}


public MessageBean getMessageBean() {
	return messageBean;
}

public void setMessageBean(MessageBean messageBean) {
	this.messageBean = messageBean;
}

public String message(){
	messageList = messageBean.findAll();
	return "message";
}
public String liuyan(){//����
	try{
		if(message.getPerson().trim().equals("")){	
			message.setPerson("����");
		}
		if(message.getTelephone()==""){
			message.setTelephone(" ");
		}
		messageBean.save(message);
		messageList = messageBean.findAll();
		return "message";
	}catch(Exception e){
		ActionContext.getContext().getSession().put("error", "���Գ���");
		return "error";
	}
	
}
public Integer getMessageId() {
	return messageId;
}
public void setMessageId(Integer messageId) {
	this.messageId = messageId;
}
public String getOperationState() {
	return operationState;
}
public void setOperationState(String operationState) {
	this.operationState = operationState;
}
public Page getPage() {
	return page;
}
public void setPage(Page page) {
	this.page = page;
}
public boolean isShowTip() {
	return isShowTip;
}
public void setShowTip(boolean isShowTip) {
	this.isShowTip = isShowTip;
}
public void setMessageList(List<Message> messageList) {
	this.messageList = messageList;
}

}
