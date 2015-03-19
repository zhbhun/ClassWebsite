package com.spring.service;

import java.util.List;

import com.common.Page;
import com.hibernate.dao.LinkDAO;
import com.hibernate.domain.Link;

public class LinkBean {
  private LinkDAO linkDAO;

public LinkDAO getLinkDAO() {
	return linkDAO;
}

public void setLinkDAO(LinkDAO linkDAO) {
	this.linkDAO = linkDAO;
}
  //zhb*****************************************
//get link
public List<Link> getLinkForAdmin(Page page){
	List<Link> list;
	page.setPageSize(10);
	 page.setSearchByOrderOfTime(false);
	 list = linkDAO.getLinkForAdmin(page);
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
//add
public boolean addLink(Link link){
	
	return linkDAO.addLink(link);
}
//getByid
public Link getLinkById(int id){
	return linkDAO.getLinkById(id);
}
//change
public boolean changeLink(Link link){
	return linkDAO.changeLink(link);
	
}
//delete
public boolean deleteLink(int  id){
	return linkDAO.deleteLink(id);
	
}
//************************************************
}
