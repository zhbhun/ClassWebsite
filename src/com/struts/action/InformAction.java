package com.struts.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.common.Page;
import com.hibernate.domain.Administrator;
import com.hibernate.domain.Inform;
import com.hibernate.domain.InformComment;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.spring.service.InformBean;

public class InformAction extends ActionSupport {
	private Inform inform;
	private InformBean informBean;
	private List list ;
	private List<Inform> informList;
	private String operationState;
	private Page page;
	private boolean isShowTip;
	//张华斌****************************************************************
	//通知管理
	public String accessInformManager(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		return "informManager";
	}
	//管理员获取通知列表,用于管理员查看通知列表，需要提供当前页信息，存于page变量
	public String getInformsByTimeAndPageForManager(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(page!=null){
	    informList = informBean.getInformsForManager(page);
	    if(informList!=null && informList.size()> 0)
	      System.out.println(informList.size());
	    else 
	      setOperationState("没有通知");
		}
		else{
			setOperationState("没有通知");
			informList = new ArrayList<Inform>();
		}
		ActionContext.getContext().getSession().put("informPage", page);
		return "admin_informlist";
	}
	//翻页查看通知
	public String changeInformPage(){
		if(page==null){
			setShowTip(true);
			setOperationState("请输入当前页数");
			informList = new ArrayList<Inform>();
			return "admin_informlist";
		}
		int currentPage = page.getCurrentPage();
		page = (Page)ActionContext.getContext().getSession().get("informPage");
		
		if(page!=null){
			System.out.println(page);
			 page.setCurrentPage(currentPage);
			 
			 informList = informBean.getInformsForManager(page);
			 if(informList!=null && informList.size()> 0)
			      System.out.println(informList.size());
			  else 
			      setOperationState("没有通知");
		}
		else{
			setOperationState("没有通知");
			informList = new ArrayList<Inform>();
		}
		ActionContext.getContext().getSession().put("informPage", page);
		return "admin_informlist";
	}
	//访问通知添加通知页面
	public String accessAddInformJsp(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		return "addInform";
	}
	//管理员添加通知
	public String addInform(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		Administrator admin = new Administrator();
		admin.setId(1);
		inform.setAdministrator(admin);
		boolean isSuccess = informBean.addInform(inform);
		if(isSuccess)
			setOperationState(inform.getName()+"发布成功");
		else{
			setOperationState(inform.getName()+"发布失败");
			setShowTip(true);
		}
		return "addinform_result";
	}
	//访问修改通知界面
	public String accessChangeInformJsp(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(inform!=null){
			System.out.println("inbform-id:"+inform.getId());
			 inform = informBean.getInformById(inform.getId());
			if(inform!=null)
				setOperationState("访问成功");
			else{
				setShowTip(true);
				setOperationState("访问出错,没有该通知,可能该通知已删除");
			}
		}
		else{
			setShowTip(true);
			setOperationState("访问出错,请你指定要修改的通知");
		}
		return "changeInform";
	}
	//编辑通知
	public String changeInform(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		
		
		if(inform!=null){
			
			System.out.println("inbform-id:"+inform.getId());
			boolean isSuccess = informBean.change(inform);
			if(isSuccess)
				setOperationState("修改成功");
			else{
				setOperationState("修改失败");
				setShowTip(true);
			}
		}
		else{
			setOperationState("修改失败");
			setShowTip(true);
		}
		return "change_result";
	}
	//管理员删除通知
	public String deleteInform(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(inform!=null){
			System.out.println("inbform-id:"+inform.getId());
		boolean isSuccess = informBean.delete(inform.getId());
		if(isSuccess)
			setOperationState("删除成功");
		else{
			setOperationState("删除失败");
		    setShowTip(true);	
		}
		}
		else{
			setOperationState("删除失败");
			setShowTip(true);	
		}
		setShowTip(true);
		return "delete_result";
	}
	
	//******************************************************************************************
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Inform getInform() {
		return inform;
	}
	public void setInform(Inform inform) {
		this.inform = inform;
	}
	public InformBean getInformBean() {
		return informBean;
	}
	public void setInformBean(InformBean informBean) {
		this.informBean = informBean;
	}
	

	public String inform(){
		
		list = informBean.findALl();
		return "inform";
	}
	
	public String comment(){
		inform = informBean.findById(inform.getId());
	    Set<InformComment> set = inform.getInformComments();
	    list = new ArrayList();
	    for(InformComment i:set){
	    	list.add(i);
	    }
		return "comment";
	}
	public List<Inform> getInformList() {
		return informList;
	}
	public void setInformList(List<Inform> informList) {
		this.informList = informList;
	}
	public String getOperationState() {
		return operationState;
	}
	public void setOperationState(String operationState) {
		this.operationState = operationState;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public boolean isShowTip() {
		return isShowTip;
	}
	public void setShowTip(boolean isShowTip) {
		this.isShowTip = isShowTip;
	}
	
}
