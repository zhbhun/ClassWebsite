package com.spring.service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.common.Page;
import com.hibernate.dao.MessageDAO;
import com.hibernate.domain.Message;

public class MessageBean {
   private MessageDAO messageDAO;

public MessageDAO getMessageDAO() {
	return messageDAO;
}

public void setMessageDAO(MessageDAO messageDAO) {
	this.messageDAO = messageDAO;
}
public void save(Message transientInstance) {
	 Date utilDate = new Date();
	 Timestamp stp = new Timestamp(utilDate.getTime());
	 transientInstance.setTime(stp);
	 messageDAO.save(transientInstance);
}

public Message findById(int id){
	return messageDAO.findById(id);
}
public void delete(int id){
	Message m = messageDAO.findById(id);
	messageDAO.delete(m);
}
public List findAll(){
	List messages = messageDAO.findAll();
	return messages;
}

//张华斌****************************************************************
//留言管理
//获取留言
public List<Message> getMessageForAdmin(Page page){
	List<Message>  list;
	page.setPageSize(5);
	page.setSearchByOrderOfTime(true);
	page.setSearchByName(false);
	list = messageDAO.getMessageForAdmin(page);
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
//删除留言
public boolean deleteMessageForAdmin(int id){
	return messageDAO.deleteMessageForAdmin(id);
	
}
//*******************************************
}
