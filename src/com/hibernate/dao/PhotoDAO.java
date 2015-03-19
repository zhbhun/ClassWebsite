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
import com.hibernate.domain.Photo;

/**
 * A data access object (DAO) providing persistence and search support for Photo
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hibernate.domain.Photo
 * @author MyEclipse Persistence Tools
 */

public class PhotoDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PhotoDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String INTRODUCTION = "introduction";
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(Photo transientInstance) {
		log.debug("saving Photo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Photo persistentInstance) {
		log.debug("deleting Photo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Photo findById(java.lang.Integer id) {
		log.debug("getting Photo instance with id: " + id);
		try {
			Photo instance = (Photo) getHibernateTemplate().get(
					"com.hibernate.domain.Photo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Photo instance) {
		log.debug("finding Photo instance by example");
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
		log.debug("finding Photo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Photo as model where model."
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

	public List findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Photo instances");
		try {
			String queryString = "from Photo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Photo merge(Photo detachedInstance) {
		log.debug("merging Photo instance");
		try {
			Photo result = (Photo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Photo instance) {
		log.debug("attaching dirty Photo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Photo instance) {
		log.debug("attaching clean Photo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PhotoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PhotoDAO) ctx.getBean("PhotoDAO");
	}
	  //zhb*************************************
	// //��ȡ�����Ƭ
	public List<Photo> getPhotoByAlbumIdForAdmin(Album album,Page page){
		List<Photo> list;
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Album tmp_album = (Album)session.get(Album.class, album.getId());
			  if(tmp_album==null)
				  return null;
			  album.setName(tmp_album.getName());
			  album.setIntroduction(tmp_album.getIntroduction());
			  Criteria criteria = session.createCriteria(Photo.class);
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  criteria.add(Restrictions.eq("album", tmp_album));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  
			  
			  criteria = session.createCriteria(Photo.class);
			  if(page.isSearchByOrderOfTime())
					 criteria.addOrder(Order.desc("time"));
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  criteria.add(Restrictions.eq("album", album));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Photo>)criteria.list();
		      transaction.commit();
		      return list;
		  }
		  catch(Exception ex){
		    	ex.printStackTrace();
				transaction.rollback();
				return null;
		  }
	}
	public Photo getPhotoByIdForAdmin(int id){
		 Photo photo;
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			 
			  photo = (Photo)session.get(Photo.class, id);
			  transaction.commit();
			  return photo;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return null;
		}
	}
	//add
	public boolean addPhotoForAlbum(Photo photo){
		
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			 
			  Integer id = (Integer)session.save(photo);
			  if(id != null)
				  photo.setId(id);
			 
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
	}
	//delette
	public boolean deletePhoto(int id){
		 Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Photo photo = (Photo)session.get(Photo.class, id);
			  session.delete(photo);
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
	//*********************************************************
}