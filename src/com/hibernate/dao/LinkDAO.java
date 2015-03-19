package com.hibernate.dao;

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

import com.common.Page;
import com.hibernate.domain.Link;

/**
 * A data access object (DAO) providing persistence and search support for Link
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hibernate.domain.Link
 * @author MyEclipse Persistence Tools
 */

public class LinkDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(LinkDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String ADDRESS = "address";

	protected void initDao() {
		// do nothing
	}

	public void save(Link transientInstance) {
		log.debug("saving Link instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Link persistentInstance) {
		log.debug("deleting Link instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Link findById(java.lang.Integer id) {
		log.debug("getting Link instance with id: " + id);
		try {
			Link instance = (Link) getHibernateTemplate().get(
					"com.hibernate.domain.Link", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Link instance) {
		log.debug("finding Link instance by example");
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
		log.debug("finding Link instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Link as model where model."
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

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findAll() {
		log.debug("finding all Link instances");
		try {
			String queryString = "from Link";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Link merge(Link detachedInstance) {
		log.debug("merging Link instance");
		try {
			Link result = (Link) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Link instance) {
		log.debug("attaching dirty Link instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Link instance) {
		log.debug("attaching clean Link instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LinkDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LinkDAO) ctx.getBean("LinkDAO");
	}
	//zhb*****************************************
	//get link
	
	public List<Link> getLinkForAdmin(Page page){
		List<Link> list;
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Criteria criteria = session.createCriteria(Link.class);
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  
			  criteria = session.createCriteria(Link.class);
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Link>)criteria.list();
		      transaction.commit();
		      return list;
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
	}
	//add
	public boolean addLink(Link link){
		 Session session = getSessionFactory().getCurrentSession();
		 Transaction  transaction = session.beginTransaction();
		 try{
			 Integer id = (Integer)session.save(link);
			  if(id != null)
				  link.setId(id);
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
	}
	//getById
	public Link getLinkById(int id){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Link c = (Link)session.get(Link.class, id);
			  transaction.commit();
			  return c;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return null;
		}
	}
	//change
	public boolean changeLink(Link link){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Link c = (Link)session.get(Link.class, link.getId());
			  if(c==null)
				  return false;
			  c.setName(link.getName());
			  c.setAddress(link.getAddress());
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
	//delete
	public boolean deleteLink(int id){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Link c = (Link)session.get(Link.class, id);
			  if(c==null)
				  return false;
			  session.delete(c);
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
	//*****************************************
}