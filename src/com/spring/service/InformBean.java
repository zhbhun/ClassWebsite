package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.common.Page;
import com.hibernate.dao.InformCommentDAO;
import com.hibernate.dao.InformDAO;
import com.hibernate.domain.Inform;
import com.hibernate.domain.InformComment;

public class InformBean {
	private InformDAO informDAO;


	public InformDAO getInformDAO() {
		return informDAO;
	}

	public void setInformDAO(InformDAO informDAO) {
		this.informDAO = informDAO;
	}
	
	public List<Inform> findALl(){
		List<Inform> informs = informDAO.findAll();
		return informs;
	}
	
	public Inform findById(Integer id){
		Inform inform = informDAO.findById(id);
		return inform;
	}
	//张华斌****************************************************************
	//管理员获取通知列表,用于管理员查看通知列表，需要提供当前页信息，存于page
	public List<Inform> getInformsForManager(Page page){
		List<Inform>  list;
		page.setPageSize(10);
		page.setSearchByOrderOfTime(true);
		//page.setSearchByName(false);
		list = informDAO.getInformsForManager(page);
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
	//管理员发布通知
	public boolean addInform(Inform inform){
		System.out.println(inform);
		if(inform != null && !(inform.getName().equals("")) && !(inform.getContent().equals(""))){

	    	 java.util.Date utilDate = new java.util.Date();
	    	 java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
	    	 inform.setTime(stp);
	    	 if(informDAO.add(inform)){
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
	//根据获得通知
	public Inform getInformById(int id){
		return  informDAO.getById(id);
		
	}
	//管理员修改通知
	public boolean change(Inform inform){
		return informDAO.update(inform);
	}
	//管理员删除通知
	public boolean delete(int id){
		boolean isSuccess = informDAO.delete(id);
		if(isSuccess)
			return true;
		else
			return false;
		
	}
	
	//******************************************************
}
