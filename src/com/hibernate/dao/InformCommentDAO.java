package com.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hibernate.domain.Inform;
import com.hibernate.domain.InformComment;

/**
 * A data access object (DAO) providing persistence and search support for
 * InformComment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hibernate.domain.InformComment
 * @author MyEclipse Persistence Tools
 */

public class InformCommentDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(InformCommentDAO.class);
	// property constants
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(InformComment transientInstance) {
		log.debug("saving InformComment instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
			//getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(InformComment persistentInstance) {
		log.debug("deleting InformComment instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public InformComment findById(java.lang.Integer id) {
		log.debug("getting InformComment instance with id: " + id);
		try {
			InformComment instance = (InformComment) getHibernateTemplate()
					.get("com.hibernate.domain.InformComment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(InformComment instance) {
		log.debug("finding InformComment instance by example");
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

	public List<InformComment> findByProperty(String propertyName, Object value) {
		log.debug("finding InformComment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from InformComment as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all InformComment instances");
		try {
			String queryString = "from InformComment";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public InformComment merge(InformComment detachedInstance) {
		log.debug("merging InformComment instance");
		try {
			InformComment result = (InformComment) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(InformComment instance) {
		log.debug("attaching dirty InformComment instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(InformComment instance) {
		log.debug("attaching clean InformComment instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InformCommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (InformCommentDAO) ctx.getBean("InformCommentDAO");
	}
	///张华斌****************************************************************
	//通知评论管理
	  //获得指定通知的评论
	public List<InformComment> getInformCommentByInformId(int informId){
		List<InformComment> list;
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Inform inform = new Inform();
			  inform.setId(informId);
			  Criteria criteria = session.createCriteria(InformComment.class);
			  criteria.add(Restrictions.eq("inform", inform));
			  list = criteria.list();
			  transaction.commit();
			  return list;
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				list = new ArrayList<InformComment>();
				return list;
		  }
         
	}
	//删除评论
	public List<InformComment> deleteInformComment(int id){
		List<InformComment> list;
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  InformComment ic = (InformComment)session.get(InformComment.class,id );
			  Inform inform = ic.getInform();
			  session.delete(ic);
			  session.flush();
			  Criteria criteria = session.createCriteria(InformComment.class);
			  criteria.add(Restrictions.eq("inform", inform));
			  list = criteria.list();
			  transaction.commit();
			  return list;
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				list = new ArrayList<InformComment>();
				return list;
		  }
	}
	//******************************************************************
}