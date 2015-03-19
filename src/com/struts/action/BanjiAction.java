package com.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.hibernate.domain.Banji;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.BanjiBean;

public class BanjiAction extends ActionSupport {
    private BanjiBean banjiBean;
    private Banji banji;
	  private String operationState;
    private boolean isShowTip;
	
	//zhb*****************************************
    //getbanji
    public String getBanjiForAdmin(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  
	  banji = banjiBean.getBanjiForAdmin();
	  if(banji==null)
		  banji = new Banji();
	  return "admin_banji";
  }
    //get  informfatioon of class 
    public String accessChange(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
	  
	  banji = banjiBean.getBanjiForAdmin();
	  if(banji==null)
		  banji = new Banji();
	  return "admin_change_banji";
  }
    //change
    public String changeBanji(){
    	if(banji==null){
    		banji = new Banji();
    		setShowTip(true);
    		setOperationState("ÐÞ¸ÄÊ§°Ü");
    		return "change_result";
    	}
    	banji = banjiBean.changeBanji(banji);
    	if(banji==null){
    		banji = new Banji();
    		setShowTip(true);
    		setOperationState("ÐÞ¸ÄÊ§°Ü");
    		return "change_result";
    	}
    	return "change_result";
    }
	
	public Banji getBanji() {
		return banji;
	}
	public void setBanji(Banji banji) {
		this.banji = banji;
	}
	public BanjiBean getBanjiBean() {
		return banjiBean;
	}
	public void setBanjiBean(BanjiBean banjiBean) {
		this.banjiBean = banjiBean;
	}
	public String intro(){
		 banji = (Banji)banjiBean.findById(1);
		return "success";
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
