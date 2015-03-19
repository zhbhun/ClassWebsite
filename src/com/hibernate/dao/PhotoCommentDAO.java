package com.hibernate.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hibernate.domain.PhotoComment;


/**
 * A data access object (DAO) providing persistence and search support for
 * PhotoComment entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hibernate.domain.PhotoComment
 * @author MyEclipse Persistence Tools
 */



public class PhotoCommentDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(PhotoCommentDAO.class);
	// property constants
	public static final String TIME = "time";
	public static final String CONTEXT = "context";

	protected void initDao() {
		// do nothing
	}

	public void save(PhotoComment transientInstance) {
		log.debug("saving PhotoComment instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(PhotoComment persistentInstance) {
		log.debug("deleting PhotoComment instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public PhotoComment findById(java.lang.Integer id) {
		log.debug("getting PhotoComment instance with id: " + id);
		try {
			PhotoComment instance = (PhotoComment) getHibernateTemplate().get(
					"com.hibernate.domain.PhotoComment", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(PhotoComment instance) {
		log.debug("finding PhotoComment instance by example");
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
		log.debug("finding PhotoComment instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from PhotoComment as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findByContext(Object context) {
		return findByProperty(CONTEXT, context);
	}

	public List findAll() {
		log.debug("finding all PhotoComment instances");
		try {
			String queryString = "from PhotoComment";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public PhotoComment merge(PhotoComment detachedInstance) {
		log.debug("merging PhotoComment instance");
		try {
			PhotoComment result = (PhotoComment) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(PhotoComment instance) {
		log.debug("attaching dirty PhotoComment instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(PhotoComment instance) {
		log.debug("attaching clean PhotoComment instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PhotoCommentDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (PhotoCommentDAO) ctx.getBean("PhotoCommentDAO");
	}
}