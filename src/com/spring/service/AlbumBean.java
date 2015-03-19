package com.spring.service;

import com.common.Page;
import com.hibernate.dao.AlbumDAO;
import com.hibernate.domain.Album;

import java.util.*;
public class AlbumBean {
  private AlbumDAO albumDAO;

public AlbumDAO getAlbumDAO() {
	return albumDAO;
}

public void setAlbumDAO(AlbumDAO albumDAO) {
	this.albumDAO = albumDAO;
}
  //�Ż���****************************************************************
//֪ͨ����
//��ȡ����б�
public List<Album> getAlbumForAdmin(Page page){
	 List<Album> list;
	 page.setPageSize(10);
	 page.setSearchByOrderOfTime(true);
	 list = albumDAO.getAlbumForAdmin(page);
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

//�������
public boolean addAlbum(Album album){
	if(album!=null &&!(album.getName().equals(""))){
		 java.util.Date utilDate = new java.util.Date();
    	 java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
    	 album.setTime(stp) ;   
    	 if(albumDAO.addAlbum(album)){
	    	  System.out.println("add succcess");
	    	  return true;
	    	 }
	    	 else{
	    		 System.out.println("add fail");
	    		 return false;
	    	 }
	}
	System.out.println("add fail");
	return false;
}
//ɾ�����
public boolean deleteAlbum(int id){
	return albumDAO.deleteAlbum(id);
}
//geybyId
public Album getAlbumById(int id){
	return albumDAO.getAlbumById(id);
}
//change
public boolean changeAlbum(Album album){
	return albumDAO.changeAlbum(album);
	
}
//***********************************************
}
