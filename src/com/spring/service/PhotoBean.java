package com.spring.service;

import java.util.List;

import com.common.Page;
import com.hibernate.dao.PhotoDAO;
import com.hibernate.domain.*;
 
public class PhotoBean {
   private PhotoDAO photoDAO;

public PhotoDAO getPhotoDAO() {
	return photoDAO;
}

public void setPhotoDAO(PhotoDAO photoDAO) {
	this.photoDAO = photoDAO;
}
   //zhb*************************************
// //��ȡ�����Ƭ
public List<Photo> getPhotoByAlbumIdForAdmin(Album album,Page page){
	List<Photo> list;
	 page.setPageSize(10);
	 page.setSearchByOrderOfTime(true);
	 list = photoDAO.getPhotoByAlbumIdForAdmin(album, page);
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
public Photo getPhotoByIdForAdmin(int id){
	return photoDAO.getPhotoByIdForAdmin(id);
}
//�ϴ���Ƭ
public boolean addPhotoForAlbum(Photo photo){
	
   return photoDAO.addPhotoForAlbum(photo);	
	
	
}
//ɾ����Ƭ
public boolean deletePhoto(int id){
	return photoDAO.deletePhoto(id);
}
//*********************************************** 
}
