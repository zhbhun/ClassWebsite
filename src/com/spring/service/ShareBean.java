package com.spring.service;

import java.util.List;

import com.common.Page;
import com.hibernate.dao.ShareDAO;
import com.hibernate.domain.Share;

public class ShareBean {
  private ShareDAO shareDAO;

public ShareDAO getShareDAO() {
	return shareDAO;
}

public void setShareDAO(ShareDAO shareDAO) {
	this.shareDAO = shareDAO;
}
//zhb*********************************
//getShare
public List<Share> getShareForAdmin(Page page){
	List<Share> list;
	page.setPageSize(10);
	 page.setSearchByOrderOfTime(true);
	 list = shareDAO.getShareForAdmin(page);
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
//delete
public boolean deleteShare(int id){
	return shareDAO.deleteShare(id);
}
//add
public boolean addShare(Share share){
	java.util.Date utilDate = new java.util.Date();
	 java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
	 share.setTime(stp);
	return shareDAO.addShare(share);
	
}

}
