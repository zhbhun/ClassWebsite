package com.struts.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.omg.CORBA.Request;

import com.common.Page;
import com.hibernate.domain.*;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.ShareBean;;
public class ShareAction extends ActionSupport {
 private static final int BUFFER_SIZE = 16 * 1024;
  private ShareBean shareBean;
  
  private Integer shareId;
  private Share share;
  private File upload;
  private String uploadFileName;
  private String uploadContentType;
  
  private List<Share> shareList;
  private Page page;
  private String operationState;
  private boolean isShowTip;
  //zhb*********************************
  //getShare
  public String getShareForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  if(page==null){
		  page = new Page();
		  page.setCurrentPage(1);
	  }
	  shareList = shareBean.getShareForAdmin(page);
	  if(shareList==null)
		  shareList = new ArrayList<Share>();
	  ActionContext.getContext().getSession().put("sharePage", page);
	  return "admin_sharelist";
  }
  
  //changeSharePage
  public String changeSharePage(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  int currentPage ;
	 if(page == null)
			currentPage = 1;
	  else
			currentPage = page.getCurrentPage();
	 page = (Page)ActionContext.getContext().getSession().get("sharePage");
	 if(page == null)
		 page =  new Page(); 
	 page.setCurrentPage(currentPage);
	 shareList = shareBean.getShareForAdmin(page);
	  if(shareList==null)
		  shareList = new ArrayList<Share>();
	  ActionContext.getContext().getSession().put("sharePage", page);
	  return "admin_sharelist";
  }
  
  //delete  
  public String deleteShare(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  page = (Page)ActionContext.getContext().getSession().get("sharePage");
	  if(page==null){
		  page = new Page();
		 page.setCurrentPage(1);  
	  }
	  if(shareId==null)
		  return "delete_result";
	  boolean isSuccess = shareBean.deleteShare(shareId);
	 
	  return "delete_result";
  }
  //add
  public String addShare(){
//		Student student = (Student)ActionContext.getContext().getSession().get("student");
//		if(student==null){
//			//return "add_result";
//		}  
	  Student student = new Student();
	  student.setId(1);
	  share.setStudent(student);
	  if(upload!=null){
	  
	  SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
	  String datetime=dateformat1.format(new Date());
	  
	  String type = uploadFileName.substring(uploadFileName.lastIndexOf("."), uploadFileName.length());
	  
	  String fileName = student.getId()+"__"+datetime+type;
	  
	  String dstPath = ServletActionContext.getServletContext().getRealPath("attachment")+ "\\" + fileName;
	  
      System.out.println(dstPath);
      System.out.println("上传的文件的类型："+ this.getUploadContentType());
      File dstFile = new File(dstPath);
      boolean isSuccess = copy(this.upload, dstFile);
      if(!isSuccess){
    	  return "error";
      }
      share.setAttachment("attachment/"+fileName);
	  }
	  boolean isSuccess = shareBean.addShare(share);
      return "add_result";
  }
  
//getShare for user
  public String getShareForUser(){
	  if(page==null){
		  page = new Page();
		  page.setCurrentPage(1);
	  }
	  shareList = shareBean.getShareForAdmin(page);
	  if(shareList==null)
		  shareList = new ArrayList<Share>();
	  ActionContext.getContext().getSession().put("sharePage", page);
	  return "user_sharelist";
  }
  
  //changeSharePage for user
  public String changeSharePageForUser(){

	 int currentPage ;
	 if(page == null)
			currentPage = 1;
	  else
			currentPage = page.getCurrentPage();
	 page = (Page)ActionContext.getContext().getSession().get("sharePage");
	 if(page == null)
		 page =  new Page(); 
	 page.setCurrentPage(currentPage);
	 shareList = shareBean.getShareForAdmin(page);
	  if(shareList==null)
		  shareList = new ArrayList<Share>();
	  ActionContext.getContext().getSession().put("sharePage", page);
	  return "user_sharelist";
  }
  private static boolean copy(File src, File dst) {
      InputStream in = null;
      OutputStream out = null;
      try {
          in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
          out = new BufferedOutputStream(new FileOutputStream(dst),
                  BUFFER_SIZE);
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

public ShareBean getShareBean() {
	return shareBean;
}

public void setShareBean(ShareBean shareBean) {
	this.shareBean = shareBean;
}

public Integer getShareId() {
	return shareId;
}

public void setShareId(Integer shareId) {
	this.shareId = shareId;
}

public Share getShare() {
	return share;
}

public void setShare(Share share) {
	this.share = share;
}

public File getUpload() {
	return upload;
}

public void setUpload(File upload) {
	this.upload = upload;
}

public String getUploadFileName() {
	return uploadFileName;
}

public void setUploadFileName(String uploadFileName) {
	this.uploadFileName = uploadFileName;
}

public String getUploadContentType() {
	return uploadContentType;
}

public void setUploadContentType(String uploadContentType) {
	this.uploadContentType = uploadContentType;
}

public List<Share> getShareList() {
	return shareList;
}

public void setShareList(List<Share> shareList) {
	this.shareList = shareList;
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
