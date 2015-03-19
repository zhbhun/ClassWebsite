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
import com.hibernate.domain.Inform;

/**
 * A data access object (DAO) providing persistence and search support for
 * Inform entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.domain.Inform
 * @author MyEclipse Persistence Tools
 */

public class InformDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(InformDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(Inform transientInstance) {
		log.debug("saving Inform instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Inform persistentInstance) {
		log.debug("deleting Inform instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Inform findById(java.lang.Integer id) {
		log.debug("getting Inform instance with id: " + id);
		try {
			Inform instance = (Inform) getHibernateTemplate().get(
					"com.hibernate.domain.Inform", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Inform instance) {
		log.debug("finding Inform instance by example");
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
		log.debug("finding Inform instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Inform as model where model."
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

	public List<Inform> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Inform> findAll() {
		log.debug("finding all Inform instances");
		try {
			String queryString = "from Inform";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Inform merge(Inform detachedInstance) {
		log.debug("merging Inform instance");
		try {
			Inform result = (Inform) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Inform instance) {
		log.debug("attaching dirty Inform instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Inform instance) {
		log.debug("attaching clean Inform instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static InformDAO getFromApplicationContext(ApplicationContext ctx) {
		return (InformDAO) ctx.getBean("InformDAO");
	}
	//张华斌****************************************************************
	//通知管理
	public List<Inform> getInformsForManager(Page page){
		  List<Inform> list;
		  Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  
			  Criteria criteria = session.createCriteria(Inform.class);
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  
			  
			  criteria = session.createCriteria(Inform.class);
			  if(page.isSearchByOrderOfTime())
					 criteria.addOrder(Order.desc("time"));
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Inform>)criteria.list();
		      transaction.commit();
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
          return list;
      }
	//添加通知
	 public boolean add(Inform inform){
   	      Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  
			  Integer id = (Integer)session.save(inform);
			  if(id != null)
				  inform.setId(id);
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
   }
	 //根据id获取通知
	 public Inform getById(int id){
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  return (Inform)session.get(Inform.class, id);
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return null;
		}
	 }
	 //修改通知
	 public boolean update(Inform instance) {
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Inform oldInform = (Inform)session.get(Inform.class,instance.getId());
			  oldInform.setName(instance.getName());
			  oldInform.setContent(instance.getContent());
			  session.saveOrUpdate(oldInform);
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

	 //删除通知
	 public boolean delete(int id){
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Inform inform = (Inform)session.get(Inform.class, id);
			  session.delete(inform);
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
	 //*************************************************************************************
}