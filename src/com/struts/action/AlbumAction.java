package com.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.Page;
import com.hibernate.domain.Administrator;
import com.hibernate.domain.Album;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.AlbumBean;

public class AlbumAction extends ActionSupport {
 private AlbumBean albumBean;
private Album album;
 private Integer albumId;
 
 private List<Album> albumList;
private Page page;
 private String operationState;
private boolean isShowTip;
//张华斌****************************************************************
//通知管理
//获取相册列表
public String getAlbumForAdmin(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	if(page==null){
		page = new Page();
		page.setCurrentPage(1);
	}
	albumList = albumBean.getAlbumForAdmin(page);
	 if(albumList==null)
		albumList = new ArrayList<Album>();
	 ActionContext.getContext().getSession().put("albumPage", page);
	 return "admin_albumlist";
}
public String showAlbum(){
	
	if(page==null){
		page = new Page();
		page.setCurrentPage(1);
	}
	albumList = albumBean.getAlbumForAdmin(page);
	 if(albumList==null)
		albumList = new ArrayList<Album>();
	 ActionContext.getContext().getSession().put("albumPage", page);
	return "album_list";
}

//翻页查看相册
public String changeAlbumpage(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	int currentPage ;
	if(page==null)
		currentPage = 1;
	else
	   currentPage = page.getCurrentPage();
	page = (Page)ActionContext.getContext().getSession().get("albumPage");
	if(page == null)
		page = new Page();
	page.setCurrentPage(currentPage);
	albumList = albumBean.getAlbumForAdmin(page);
	 if(albumList==null)
		albumList = new ArrayList<Album>();
	 ActionContext.getContext().getSession().put("albumPage", page);
	 return "admin_albumlist";
}

public String changeAlbumpageForUser(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	int currentPage ;
	if(page==null)
		currentPage = 1;
	else
	   currentPage = page.getCurrentPage();
	page = (Page)ActionContext.getContext().getSession().get("albumPage");
	if(page == null)
		page = new Page();
	page.setCurrentPage(currentPage);
	albumList = albumBean.getAlbumForAdmin(page);
	 if(albumList==null)
		albumList = new ArrayList<Album>();
	 ActionContext.getContext().getSession().put("albumPage", page);
	 return "album_list";
}

//添加相册
public String addAlbum(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	Administrator admin = new Administrator();
	admin.setId(1);
	album.setAdministrator(admin);
	 
	boolean isSuccess = albumBean.addAlbum(album);
	return "addalbum_result";
}


//访问待修改的相册信息
public void accessChangeAlbum(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	System.out.println(albumId);
	if(albumId==null)
		return ;
	Album album = albumBean.getAlbumById(albumId);
	if(albumId==null)
		return ;
	String json = "";
	json += "{\"id\":\""+album.getId()+"\","; 
	if(album.getName()!=null)
	  json += "\"name\":\""+album.getName().replace("\"", "\\\"")+"\",";
	else
		 json += "\"name\":\"\",";
	if(album.getIntroduction()!=null)
	   json +="\"introduction\":\""+album.getIntroduction().replace("\"", "\\\"")+"\"}";
	else
		json +="\"introduction\":\"\"}";
	HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("application/json;charset=UTF-8");  
	  response.setHeader("Charset","UTF-8"); 
	        PrintWriter out = null;
	        try {
	   		   out = response.getWriter();
	   		  } catch (IOException e) {
	   		   // TODO Auto-generated catch block
	   		   e.printStackTrace();
	   		  }
	   out.write(json);
	  out.flush();  
	
}
//修改相册
public String changeAlbum(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	page = (Page)ActionContext.getContext().getSession().get("albumPage");
	if(page == null){
		page = new Page();
		page.setCurrentPage(1);
	}
	if(album==null)
		return "change_result";
	boolean isSuccess = albumBean.changeAlbum(album);
	return "change_result";
}
//删除相册
public String deleteAlbum(){
//	Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//	if(admin==null){
//		//return "admin_notLogin";
//	}
	page = (Page)ActionContext.getContext().getSession().get("albumPage");
	 if(page==null){
		  page = new Page();
		 page.setCurrentPage(1);  
	  }
	if(albumId==null)
		return "delete_result";
	boolean isSuccess = albumBean.deleteAlbum(albumId);
	return "delete_result";
	
}
//*******************************************************

public AlbumBean getAlbumBean() {
	return albumBean;
}

public void setAlbumBean(AlbumBean albumBean) {
	this.albumBean = albumBean;
}
public Album getAlbum() {
	return album;
}
public void setAlbum(Album album) {
	this.album = album;
}
public Integer getAlbumId() {
	return albumId;
}
public void setAlbumId(Integer albumId) {
	this.albumId = albumId;
}
public List<Album> getAlbumList() {
	return albumList;
}
public void setAlbumList(List<Album> albumList) {
	this.albumList = albumList;
}
public Page getPage() {
	return page;
}
public void setPage(Page page) {
	this.page = page;
}
public String getOperationState() {
	return operationState;
}
public void setOperationState(String operationState) {
	this.operationState = operationState;
}
public boolean isShowTip() {
	return isShowTip;
}
public void setShowTip(boolean isShowTip) {
	this.isShowTip = isShowTip;
}
 
}
