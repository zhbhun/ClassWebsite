package com.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.common.Page;
import com.hibernate.domain.Message;

/**
 * A data access object (DAO) providing persistence and search support for
 * Message entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.domain.Message
 * @author MyEclipse Persistence Tools
 */

public class MessageDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(MessageDAO.class);
	// property constants
	public static final String CONTENT = "content";
	public static final String PERSON = "person";
	public static final String TELEPHONE = "telephone";
    public static final String TIME ="time";
	protected void initDao() {
		// do nothing
	}

	public void save(Message transientInstance) {
		log.debug("saving Message instance");
		try {
			getSessionFactory().getCurrentSession().save(transientInstance);
		//	getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Message persistentInstance) {
		log.debug("deleting Message instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Message findById(java.lang.Integer id) {
		log.debug("getting Message instance with id: " + id);
		try {
			Message instance = (Message) getHibernateTemplate().get(
					"com.hibernate.domain.Message", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Message instance) {
		log.debug("finding Message instance by example");
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
		log.debug("finding Message instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Message as model where model."
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

	public List findByPerson(Object person) {
		return findByProperty(PERSON, person);
	}

	public List findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}
     public List findNewerMessage(){
    	 log.debug("finding Message instance with time");
 		try {
 			String queryString = "from Message as model order by model."
 					+ TIME + " desc";
 			return getHibernateTemplate().find(queryString);
 		} catch (RuntimeException re) {
 			log.error("find by property name failed", re);
 			throw re;
 		}
     }
	public List findAll() {
		log.debug("finding all Message instances");
		try {
			String queryString = "from Message";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Message merge(Message detachedInstance) {
		log.debug("merging Message instance");
		try {
			Message result = (Message) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Message instance) {
		log.debug("attaching dirty Message instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Message instance) {
		log.debug("attaching clean Message instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MessageDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MessageDAO) ctx.getBean("MessageDAO");
	}
	////张华斌****************************************************************
	//留言管理
	//获取留言
	public List<Message> getMessageForAdmin(Page page){
		 List<Message> list;
		  Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Criteria criteria = session.createCriteria(Message.class);
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  criteria = session.createCriteria(Message.class);
			  if(page.isSearchByOrderOfTime())
					 criteria.addOrder(Order.desc("time"));
			  criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Message>)criteria.list();
		      transaction.commit();
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
          return list;
	}
	//删除 留言
	public boolean deleteMessageForAdmin(int id){
		
		  Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Message message = (Message)session.get(Message.class,id);
			  session.delete(message);
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
	///*******************************************************
}