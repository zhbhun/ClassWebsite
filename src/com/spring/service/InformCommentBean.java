package com.spring.service;

import java.util.List;

import com.hibernate.dao.InformCommentDAO;
import com.hibernate.domain.Inform;
import com.hibernate.domain.InformComment;
import com.hibernate.domain.Student;

public class InformCommentBean {
  private InformCommentDAO informCommentDAO;

public InformCommentDAO getInformCommentDAO() {
	return informCommentDAO;
}

public void setInformCommentDAO(InformCommentDAO informCommentDAO) {
	this.informCommentDAO = informCommentDAO;
}

  public InformComment comment(String content, Inform inform, Student student ){
	  InformComment informComment = new InformComment();
	  informComment.setContent(content);
	  informComment.setInform(inform);
	  java.util.Date utilDate = new java.util.Date();
  	  java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
  	  informComment.setTime(stp);
  	  informComment.setStudent(student);
  	  informCommentDAO.save(informComment);
  	  return informComment;
  }
  ////张华斌****************************************************************
//通知评论管理
  //获得指定通知的评论
  public List<InformComment> getInformCommentByInformId(int id){
	  return informCommentDAO.getInformCommentByInformId(id);
   }
  public List<InformComment> deleteInformComment(int id){
	  return informCommentDAO.deleteInformComment(id);
  }
  
  
  
  

  
}
