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
import com.hibernate.domain.Course;

/**
 * A data access object (DAO) providing persistence and search support for
 * Course entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.domain.Course
 * @author MyEclipse Persistence Tools
 */

public class CourseDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(CourseDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String TEACHER = "teacher";
	public static final String PLACE = "place";
	public static final String TELEPHONE = "telephone";
	public static final String MAIL = "mail";
	public static final String OTHER = "other";

	protected void initDao() {
		// do nothing
	}

	public void save(Course transientInstance) {
		log.debug("saving Course instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Course persistentInstance) {
		log.debug("deleting Course instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Course findById(java.lang.Integer id) {
		log.debug("getting Course instance with id: " + id);
		try {
			Course instance = (Course) getHibernateTemplate().get(
					"com.hibernate.domain.Course", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Course instance) {
		log.debug("finding Course instance by example");
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
		log.debug("finding Course instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Course as model where model."
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

	public List findByTeacher(Object teacher) {
		return findByProperty(TEACHER, teacher);
	}

	public List findByPlace(Object place) {
		return findByProperty(PLACE, place);
	}

	public List findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List findByMail(Object mail) {
		return findByProperty(MAIL, mail);
	}

	public List findByOther(Object other) {
		return findByProperty(OTHER, other);
	}

	public List findAll() {
		log.debug("finding all Course instances");
		try {
			String queryString = "from Course";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Course merge(Course detachedInstance) {
		log.debug("merging Course instance");
		try {
			Course result = (Course) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Course instance) {
		log.debug("attaching dirty Course instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Course instance) {
		log.debug("attaching clean Course instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CourseDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CourseDAO) ctx.getBean("CourseDAO");
	}
	
	//zhb*****************************************
	//»ñÈ¡¿Î³Ì
	
	public List<Course> getCoursesForAdmin(Page page){
		List<Course> list;
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Criteria criteria = session.createCriteria(Course.class);
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  
			  criteria = session.createCriteria(Course.class);
			  if(page.isSearchByOrderOfTime())
					 criteria.addOrder(Order.desc("time"));
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      list = (List<Course>)criteria.list();
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
	public boolean addCourse(Course course){
		 Session session = getSessionFactory().getCurrentSession();
		 Transaction  transaction = session.beginTransaction();
		 try{
			 Integer id = (Integer)session.save(course);
			  if(id != null)
				  course.setId(id);
			 
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
	public Course getCourseById(int id){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Course c = (Course)session.get(Course.class, id);
			  transaction.commit();
			  return c;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return null;
		}
	}
	//delete
	public boolean deleteCourse(int id){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Course c = (Course)session.get(Course.class, id);
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
	//change
	public boolean changeCourse(Course course){
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Course c = (Course)session.get(Course.class, course.getId());
			  c.setName(course.getName());
			  c.setMail(course.getMail());
			  c.setOther(course.getOther());
			  c.setPlace(course.getPlace());
			  c.setTeacher(course.getTeacher());
			  c.setTelephone(course.getTelephone());
			  c.setTime(course.getTime());
			  session.update(c);
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
}