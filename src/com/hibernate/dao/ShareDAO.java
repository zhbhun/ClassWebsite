package com.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.Page;
import com.hibernate.domain.Share;

/**
 * A data access object (DAO) providing persistence and search support for Share
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hibernate.domain.Share
 * @author MyEclipse Persistence Tools
 */

public class ShareDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(ShareDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CONTENT = "content";
	public static final String ATTACHMENT = "attachment";

	protected void initDao() {
		// do nothing
	}

	public void save(Share transientInstance) {
		log.debug("saving Share instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Share persistentInstance) {
		log.debug("deleting Share instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Share findById(java.lang.Integer id) {
		log.debug("getting Share instance with id: " + id);
		try {
			Share instance = (Share) getHibernateTemplate().get(
					"com.hibernate.domain.Share", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Share instance) {
		log.debug("finding Share instance by example");
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
		log.debug("finding Share instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Share as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findByAttachment(Object attachment) {
		return findByProperty(ATTACHMENT, attachment);
	}

	public List findAll() {
		log.debug("finding all Share instances");
		try {
			String queryString = "from Share";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Share merge(Share detachedInstance) {
		log.debug("merging Share instance");
		try {
			Share result = (Share) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Share instance) {
		log.debug("attaching dirty Share instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Share instance) {
		log.debug("attaching clean Share instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ShareDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ShareDAO) ctx.getBean("ShareDAO");
	}
	
	//zhb*********************************
	//getShare

	public List<Share> getShareForAdmin(Page page){
		List<Share> list;
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Criteria criteria = session.createCriteria(Share.class);
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  
			  criteria = session.createCriteria(Share.class);
			  if(page.isSearchByOrderOfTime())
					 criteria.addOrder(Order.desc("time"));
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Share>)criteria.list();
		      transaction.commit();
		      return list;
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
	}
	//delete
	public boolean deleteShare(int id){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Share s = (Share)session.get(Share.class, id);
			  session.delete(s);
			  session.flush();
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
		  
		
	}
	//add
	public boolean addShare(Share share){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
		      Integer shareId = (Integer)session.save(share);
		      if(shareId==null)
		    	   return false;
		      share.setId(shareId);
		      session.flush();
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
			  
	}
	//*********************************************
}