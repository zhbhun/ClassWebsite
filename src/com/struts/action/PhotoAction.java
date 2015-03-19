package com.struts.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.common.Page;
import com.hibernate.domain.Album;
import com.hibernate.domain.Photo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.PhotoBean;

public class PhotoAction extends ActionSupport {
 private static final int BUFFER_SIZE = 16 * 1024;
  private PhotoBean photoBean;
  
  
  private Album album;
  private Photo photos;
  private Integer photoId;
  private File photo;
  private String photoFileName;
  private String photoContentType;
  
  
  private List<Photo> photoList;
  private Page page;
  private String operationState;
 private boolean isShowTip;
//zhb*****************************************
 //获取相册照片
 public String getPhotoByAlbumIdForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	 if(album == null){
		 setShowTip(true);
		 setOperationState("error:404");
		 photoList = new ArrayList<Photo>();
		 return "admin_photolist_album";
	 }
	 if(page==null){
		 page = new Page();
	     page.setCurrentPage(1);
	 }
	 
	 photoList = photoBean.getPhotoByAlbumIdForAdmin(album,page);
	 if(photoList==null)
		 photoList = new ArrayList<Photo>();
	 ActionContext.getContext().getSession().put("photoPage", page);
	 ActionContext.getContext().getSession().put("albumForPhotoPage", album);
	 return "admin_photolist_album";
 }
 public String getPhotoByAlbumId(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	 if(album == null){
		 setShowTip(true);
		 setOperationState("error:404");
		 photoList = new ArrayList<Photo>();
		 return "photolist_album";
	 }
	 if(page==null){
		 page = new Page();
	     page.setCurrentPage(1);
	 }
	 
	 photoList = photoBean.getPhotoByAlbumIdForAdmin(album,page);
	 if(photoList==null)
		 photoList = new ArrayList<Photo>();
	 ActionContext.getContext().getSession().put("photoPage", page);
	 ActionContext.getContext().getSession().put("albumForPhotoPage", album);
	 return "photolist_album";
}
 //相片翻页查看
 public String changePhotoPage(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	 album = (Album)ActionContext.getContext().getSession().get("albumForPhotoPage");
	 if(album == null){
			setShowTip(true);
			setOperationState("error:404");
			 photoList = new ArrayList<Photo>();
			 return "admin_photolist_album";
	 }
	 int currentPage ;
	 if(page == null)
		currentPage = 1;
	 else
		currentPage = page.getCurrentPage();
	 page = (Page)ActionContext.getContext().getSession().get("photoPage");
	 if(page == null)
		 page =  new Page();
	
			 page.setCurrentPage(currentPage);
			 photoList = photoBean.getPhotoByAlbumIdForAdmin(album,page);
			  if(photoList!=null)
			      System.out.println(photoList.size());
			  else 
			      setOperationState("没有通知");
		
		 ActionContext.getContext().getSession().put("photoPage", page);
		 return "admin_photolist_album";
	 
 }
 //获得指定照片
 public String getPhotoByIdForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	 if(photoId==null){
		 setShowTip(true);
		setOperationState("error:404");
		 return "admin_photo";
	 }
	 photos = photoBean.getPhotoByIdForAdmin(photoId);
	 if(photos==null){
		 setShowTip(true);
			setOperationState("error:404");
	}
	 return "admin_photo";
 }
 //删除相片
 public String deletePhoto(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	 page = (Page)ActionContext.getContext().getSession().get("photoPage");
	 if(page==null){
		 page = new Page();
		 page.setCurrentPage(1);  
	  }
	 if(photoId==null)
		 return "delete_result";
	 boolean isSuccess = photoBean.deletePhoto(photoId);
	 return "delete_result";
 }
 //上传照片
 @SuppressWarnings("deprecation")
 public void uploadPhoto() throws Exception {
	 HttpServletResponse response = ServletActionContext.getResponse();
	 response.setCharacterEncoding("utf-8");
	 if(album==null){
		 response.getWriter().print("no");
		 return;
	 }
		 
  System.out.println("begin**********************************");
  
  SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
  String datetime=dateformat1.format(new Date());
  String type = photoFileName.substring(photoFileName.lastIndexOf("."), photoFileName.length());
  String fileName = album.getId()+"__"+datetime+photoFileName;
  String dstPath = ServletActionContext.getServletContext().getRealPath("uploadsPhoto")+ "\\" +fileName;
  System.out.println(dstPath);
  File dstFile = new File(dstPath);
  boolean isSuccess = copy(this.photo, dstFile);
 
  if(!isSuccess){
	  response.getWriter().print("no");
	  return;
  }
  
  String path = "uploadsPhoto/"+fileName;
  photos = new Photo();
  photos.setName(photoFileName.substring(0, photoFileName.lastIndexOf(".")));
  photos.setAlbum(album);
  photos.setIntroduction("");
  java.util.Date utilDate = new java.util.Date();
	 java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
  photos.setTime(stp);
  photos.setContent(path);
 
  isSuccess = photoBean.addPhotoForAlbum(photos);
  System.out.println(2);
  if(!isSuccess){
	  response.getWriter().print("no");
	  return;
  }
  response.getWriter().print("yes");
 

 }
 private static boolean copy(File src, File dst) {
     InputStream in = null;
     OutputStream out = null;
     try {
         in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
         out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
         byte[] buffer = new byte[BUFFER_SIZE];
         int len = 0;
         while ((len = in.read(buffer)) > 0) {
             out.write(buffer, 0, len);
         }
        
     } catch (Exception e) {
         e.printStackTrace();
         return false;
     } finally {
         if (null != in) {
             try {
                 in.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         if (null != out) {
             try {
                 out.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     return true;
 }
 //*************************************************
public PhotoBean getPhotoBean() {
	return photoBean;
}

public void setPhotoBean(PhotoBean photoBean) {
	this.photoBean = photoBean;
}
public Album getAlbum() {
	return album;
}
public void setAlbum(Album album) {
	this.album = album;
}
public Photo getPhotos() {
	return photos;
}
public void setPhotos(Photo photos) {
	this.photos = photos;
}
public Integer getPhotoId() {
	return photoId;
}
public void setPhotoId(Integer photoId) {
	this.photoId = photoId;
}
public File getPhoto() {
	return photo;
}
public void setPhoto(File photo) {
	this.photo = photo;
}
public String getPhotoFileName() {
	return photoFileName;
}
public void setPhotoFileName(String photoFileName) {
	this.photoFileName = photoFileName;
}
public String getPhotoContentType() {
	return photoContentType;
}
public void setPhotoContentType(String photoContentType) {
	this.photoContentType = photoContentType;
}
public List<Photo> getPhotoList() {
	return photoList;
}
public void setPhotoList(List<Photo> photoList) {
	this.photoList = photoList;
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
public static int getBufferSize() {
	return BUFFER_SIZE;
}
  
}
