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
	//�Ż���****************************************************************
	//֪ͨ����
	public String accessInformManager(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		return "informManager";
	}
	//����Ա��ȡ֪ͨ�б�,���ڹ���Ա�鿴֪ͨ�б���Ҫ�ṩ��ǰҳ��Ϣ������page����
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
	      setOperationState("û��֪ͨ");
		}
		else{
			setOperationState("û��֪ͨ");
			informList = new ArrayList<Inform>();
		}
		ActionContext.getContext().getSession().put("informPage", page);
		return "admin_informlist";
	}
	//��ҳ�鿴֪ͨ
	public String changeInformPage(){
		if(page==null){
			setShowTip(true);
			setOperationState("�����뵱ǰҳ��");
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
			      setOperationState("û��֪ͨ");
		}
		else{
			setOperationState("û��֪ͨ");
			informList = new ArrayList<Inform>();
		}
		ActionContext.getContext().getSession().put("informPage", page);
		return "admin_informlist";
	}
	//����֪ͨ���֪ͨҳ��
	public String accessAddInformJsp(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		return "addInform";
	}
	//����Ա���֪ͨ
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
			setOperationState(inform.getName()+"�����ɹ�");
		else{
			setOperationState(inform.getName()+"����ʧ��");
			setShowTip(true);
		}
		return "addinform_result";
	}
	//�����޸�֪ͨ����
	public String accessChangeInformJsp(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(inform!=null){
			System.out.println("inbform-id:"+inform.getId());
			 inform = informBean.getInformById(inform.getId());
			if(inform!=null)
				setOperationState("���ʳɹ�");
			else{
				setShowTip(true);
				setOperationState("���ʳ���,û�и�֪ͨ,���ܸ�֪ͨ��ɾ��");
			}
		}
		else{
			setShowTip(true);
			setOperationState("���ʳ���,����ָ��Ҫ�޸ĵ�֪ͨ");
		}
		return "changeInform";
	}
	//�༭֪ͨ
	public String changeInform(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		
		
		if(inform!=null){
			
			System.out.println("inbform-id:"+inform.getId());
			boolean isSuccess = informBean.change(inform);
			if(isSuccess)
				setOperationState("�޸ĳɹ�");
			else{
				setOperationState("�޸�ʧ��");
				setShowTip(true);
			}
		}
		else{
			setOperationState("�޸�ʧ��");
			setShowTip(true);
		}
		return "change_result";
	}
	//����Աɾ��֪ͨ
	public String deleteInform(){
//		Administrator admin = (Administrator)ActionContext.getContext().getSession().get("admin");
//		if(admin==null){
//			//return "admin_notLogin";
//		}
		if(inform!=null){
			System.out.println("inbform-id:"+inform.getId());
		boolean isSuccess = informBean.delete(inform.getId());
		if(isSuccess)
			setOperationState("ɾ���ɹ�");
		else{
			setOperationState("ɾ��ʧ��");
		    setShowTip(true);	
		}
		}
		else{
			setOperationState("ɾ��ʧ��");
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
