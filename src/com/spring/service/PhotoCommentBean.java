package com.spring.service;

import com.hibernate.dao.PhotoCommentDAO;

public class PhotoCommentBean {
 private PhotoCommentDAO photoCommentDAO;

public PhotoCommentDAO getPhotoCommentDAO() {
	return photoCommentDAO;
}

public void setPhotoCommentDAO(PhotoCommentDAO photoCommentDAO) {
	this.photoCommentDAO = photoCommentDAO;
}
 

}
