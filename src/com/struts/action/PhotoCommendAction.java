package com.struts.action;

import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.PhotoCommentBean;



public class PhotoCommendAction extends ActionSupport {
  private PhotoCommentBean photoCommentBean;

public PhotoCommentBean getPhotoCommentBean() {
	return photoCommentBean;
}

public void setPhotoCommentBean(PhotoCommentBean photoCommentBean) {
	this.photoCommentBean = photoCommentBean;
}
  
} 
