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
import com.hibernate.domain.Album;

/**
 * A data access object (DAO) providing persistence and search support for Album
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hibernate.domain.Album
 * @author MyEclipse Persistence Tools
 */

public class AlbumDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(AlbumDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String INTRODUCTION = "introduction";

	protected void initDao() {
		// do nothing
	}

	public void save(Album transientInstance) {
		log.debug("saving Album instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Album persistentInstance) {
		log.debug("deleting Album instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Album findById(java.lang.Integer id) {
		log.debug("getting Album instance with id: " + id);
		try {
			Album instance = (Album) getHibernateTemplate().get(
					"com.hibernate.domain.Album", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Album instance) {
		log.debug("finding Album instance by example");
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
		log.debug("finding Album instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Album as model where model."
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

	public List findByIntroduction(Object introduction) {
		return findByProperty(INTRODUCTION, introduction);
	}

	public List findAll() {
		log.debug("finding all Album instances");
		try {
			String queryString = "from Album";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Album merge(Album detachedInstance) {
		log.debug("merging Album instance");
		try {
			Album result = (Album) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Album instance) {
		log.debug("attaching dirty Album instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Album instance) {
		log.debug("attaching clean Album instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AlbumDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AlbumDAO) ctx.getBean("AlbumDAO");
	}
	
	//张华斌****************************************************************
	//通知管理
	//获取相册列表
	public List<Album> getAlbumForAdmin(Page page){
		List<Album> list;
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
			 try{ 
			  Criteria criteria = session.createCriteria(Album.class);
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  criteria = session.createCriteria(Album.class);
			  if(page.isSearchByOrderOfTime())
					 criteria.addOrder(Order.desc("time"));
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Album>)criteria.list();
		      transaction.commit();
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
		    	transaction.rollback();
				return null;
		  }
          return list;
	}
	//添加相册
	public boolean addAlbum(Album album){
		  Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			
//			  Administrator admin = (Administrator)session.get(Administrator.class, album.getAdministrator().getId());
//			  album.setAdministrator(admin);
			  Integer id = (Integer)session.save(album);
			 
			  if(id != null)
				  album.setId(id);
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
	}
	//删除相册
	public boolean deleteAlbum(int id){
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Album album = (Album)session.get(Album.class, id);
			  session.delete(album);
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
	//getByid
	public Album getAlbumById(int id){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Album album = (Album)session.get(Album.class, id);
			  transaction.commit();
			  return album;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return null;
		}
	}
	//change
	public boolean changeAlbum(Album album){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Album tmp_album = (Album)session.get(Album.class, album.getId());
		      if(tmp_album==null){
		    	  transaction.commit();
		    	  return  false;
		      }
		      tmp_album.setName(album.getName());
		      tmp_album.setIntroduction(album.getIntroduction());
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
	//**************************************************************************
}