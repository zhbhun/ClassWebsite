package com.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hibernate.domain.Banji;

/**
 * A data access object (DAO) providing persistence and search support for Baiji
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hibernate.domain.Baiji
 * @author MyEclipse Persistence Tools
 */

public class BanjiDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(BanjiDAO.class);
	// property constants
	public static final String CLASS_NUM = "classNum";
	public static final String GRADE = "grade";
	public static final String MAJOR = "major";
	public static final String SCHOOL = "school";
	public static final String INDRODUCT = "indroduct";

	protected void initDao() {
		// do nothing
	}

	public void save(Banji transientInstance) {
		log.debug("saving Banji instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Banji persistentInstance) {
		log.debug("deleting Banji instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Banji findById(java.lang.Integer id) {
		log.debug("getting Banji instance with id: " + id);
		try {
			Banji instance = (Banji) getHibernateTemplate().get(
					"com.hibernate.domain.Banji", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Banji instance) {
		log.debug("finding Banji instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Banji instance with property: " + propertyName
				+ ", value: " + value);
		
		try {
			String queryString = "from Banji as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByClassNum(Object classNum) {
		return findByProperty(CLASS_NUM, classNum);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findByMajor(Object major) {
		return findByProperty(MAJOR, major);
	}

	public List findBySchool(Object school) {
		return findByProperty(SCHOOL, school);
	}

	public List findByIndroduct(Object indroduct) {
		return findByProperty(INDRODUCT, indroduct);
	}

	public List findAll() {
		log.debug("finding all Banji instances");
		try {
			String queryString = "from Banji";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Banji merge(Banji detachedInstance) {
		log.debug("merging Banji instance");
		try {
			Banji result = (Banji) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Banji instance) {
		log.debug("attaching dirty Banji instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Banji instance) {
		log.debug("attaching clean Banji instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BanjiDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BanjiDAO) ctx.getBean("BanjiDAO");
	}
	 //zhb*****************************************
    //getbanji
	public Banji getBanjiForAdmin(){
		
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Criteria criteria = session.createCriteria(Banji.class);
			  List list= criteria.list();
			  transaction.commit();
			  if(list.size()==0)
				  return null;
			  return (Banji)list.get(0);
				  
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
	}
	//change
	public Banji changeBanji(Banji banji){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Banji tmp_banji = (Banji)session.get(Banji.class, banji.getId());
			  if(banji==null)
				 return null;
			  tmp_banji.setSchool(banji.getSchool());
			  tmp_banji.setGrade(banji.getGrade());
			  tmp_banji.setClassNum(banji.getClassNum());
			  tmp_banji.setMajor(banji.getMajor());
			  tmp_banji.setIndroduct(banji.getIndroduct());
			  session.flush();
			  transaction.commit();
			  return tmp_banji;
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
	}
	//********************************************************
}