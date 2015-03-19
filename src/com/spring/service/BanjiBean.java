package com.spring.service;

import java.util.List;

import com.hibernate.dao.BanjiDAO;
import com.hibernate.domain.Banji;

public class BanjiBean {
	private BanjiDAO banjiDAO;

	public BanjiDAO getBanjiDAO() {
		return banjiDAO;
	}

	
	public void setBanjiDAO(BanjiDAO banjiDAO) {
		this.banjiDAO = banjiDAO;
	}
	
	public String getIntroduct(){
		
		return null;
	}
	public void save(Banji transientInstance){
		banjiDAO.save(transientInstance);
	}
	
	public List<Banji> findClass(){
		List<Banji> banjis = banjiDAO.findByClassNum(3);
		return banjis;
	}
	public Banji findFirstClass(){
		List<Banji> banjis = banjiDAO.findByClassNum(3);
		return banjis.get(0);
	}
	public Banji findById(Integer id){
		Banji banji = new Banji();
	try{
		 banji = banjiDAO.findById(id);
	}catch(Exception e){
		banji.setClassNum(3);
		banji.setGrade("2009");
		banji.setMajor("软件");
		banji.setSchool("山东大学");
	}
		return banji;
	
	}
	 //zhb*****************************************
    //getbanji
	public Banji getBanjiForAdmin(){
		return banjiDAO.getBanjiForAdmin();
	}
	//change
	public Banji changeBanji(Banji banji){
		return banjiDAO.changeBanji(banji);
	}
}
