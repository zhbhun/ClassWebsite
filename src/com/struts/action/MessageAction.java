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
  //张华斌****************************************************************
//通知管理
	//分页获取留言
	public String getMessageForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(page!=null){
			messageList = messageBean.getMessageForAdmin(page);
			if(messageList!=null && messageList.size()> 0)
			      setOperationState("有"+messageList.size()+"留言");
			    else 
			      setOperationState("暂时没有人留言");
		}
		else{
			setShowTip(true);
			setOperationState("当前访问页面有错请重新访问");
			messageList = new ArrayList<Message>();
			
		}
		ActionContext.getContext().getSession().put("messagePage", page);
		return "admin_messagelist";
	}
	//翻页查看留言
	public String changeMessagePage(){
		if(page==null){
			setShowTip(true);
			setOperationState("当前访问页面有错请重新访问");
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
			      setOperationState("没有留言");
		}
		else{
			page = new Page();
			page.setCurrentPage(1);
			 messageList = messageBean.getMessageForAdmin(page);
			 if(messageList!=null && messageList.size()> 0)
			      System.out.println(messageList.size());
			  else 
			      setOperationState("没有留言");
		}
		ActionContext.getContext().getSession().put("messagePage", page);
		return "admin_messagelist";
	}
//删除留言
	public String deleteMessageForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		} 
		 if(messageId==null){
			  setOperationState("出错了,没能删除您要删除的留言");
			  return "delete_result";
		 }
		 boolean isSuccess = messageBean.deleteMessageForAdmin(messageId);
		 if(isSuccess)
			setOperationState("删除成功");
		 else
			setOperationState("出错了,没能删除您要删除的留言");
		 
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
public String liuyan(){//留言
	try{
		if(message.getPerson().trim().equals("")){	
			message.setPerson("佚名");
		}
		if(message.getTelephone()==""){
			message.setTelephone(" ");
		}
		messageBean.save(message);
		messageList = messageBean.findAll();
		return "message";
	}catch(Exception e){
		ActionContext.getContext().getSession().put("error", "留言出错");
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
