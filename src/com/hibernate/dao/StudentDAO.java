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

import com.common.StudentPage;
import com.hibernate.domain.Banji;
import com.hibernate.domain.Job;
import com.hibernate.domain.Student;

/**
 * A data access object (DAO) providing persistence and search support for
 * Student entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.domain.Student
 * @author MyEclipse Persistence Tools
 */

public class StudentDAO extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(StudentDAO.class);
	// property constants
	public static final String STUDENT_NUM = "studentNum";
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String SEX = "sex";
	public static final String PHONENUMBER = "phonenumber";
	public static final String QQ = "qq";
	public static final String MAIL = "mail";
	public static final String ADDRESS = "address";
	public static final String NATIVE_PALCE = "nativePalce";
	public static final String INTRODUCTION = "introduction";

	protected void initDao() {
		// do nothing
	}

	public void save(Student transientInstance) {
		log.debug("saving Student instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Student persistentInstance) {
		log.debug("deleting Student instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Student findById(java.lang.Integer id) {
		log.debug("getting Student instance with id: " + id);
		try {
			Student instance = (Student) getHibernateTemplate().get(
					"com.hibernate.domain.Student", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Student instance) {
		log.debug("finding Student instance by example");
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
		log.debug("finding Student instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Student as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStudentNum(Object studentNum) {
		return findByProperty(STUDENT_NUM, studentNum);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByAge(Object age) {
		return findByProperty(AGE, age);
	}

	public List findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findByPhonenumber(Object phonenumber) {
		return findByProperty(PHONENUMBER, phonenumber);
	}

	public List findByQq(Object qq) {
		return findByProperty(QQ, qq);
	}

	public List findByMail(Object mail) {
		return findByProperty(MAIL, mail);
	}

	public List findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List findByNativePalce(Object nativePalce) {
		return findByProperty(NATIVE_PALCE, nativePalce);
	}

	public List findByIntroduction(Object introduction) {
		return findByProperty(INTRODUCTION, introduction);
	}

	public List findAll() {
		log.debug("finding all Student instances");
		try {
			String queryString = "from Student";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Student merge(Student detachedInstance) {
		log.debug("merging Student instance");
		try {
			Student result = (Student) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Student instance) {
		log.debug("attaching dirty Student instance");
		try {
			Student t = (Student)findByStudentNum(instance.getStudentNum()).get(0);
			
			t.setAddress(instance.getAddress());
			t.setAge(instance.getAge());
			t.setBanji(instance.getBanji());
			t.setInformComments(instance.getInformComments());
			t.setIntroduction(instance.getIntroduction());
			t.setJob(instance.getJob());
			t.setMail(instance.getMail());
			t.setName(instance.getName());
			t.setQq(instance.getQq());
			t.setNativePalce(instance.getNativePalce());
			t.setPassword(instance.getPassword());
			t.setPhonenumber(instance.getPhonenumber());
			t.setSex(instance.getSex());
			getSessionFactory().getCurrentSession().update(t);
			getSessionFactory().getCurrentSession().flush();
		//	getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Student instance) {
		log.debug("attaching clean Student instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static StudentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (StudentDAO) ctx.getBean("StudentDAO");
	}
	//zhb*****************************************
	//get student
	
	public List<Student> getStudentForAdmin(StudentPage page){
		List<Student> list;
		Session session = getSessionFactory().getCurrentSession();
		  Transaction  transaction = session.beginTransaction();
		  try{
			  Criteria criteria = session.createCriteria(Student.class);
			  if(page.isSerchByJob()){
				  Job job = new Job();
				  job.setId(page.getJobId());
				  criteria.add(Restrictions.eq("job",job));
			  }
			  if(page.isSearchByName())
					 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
			  int size = criteria.list().size();
			  page.setTotalSize(size);
			  page.setTotalPage(size/page.getPageSize()+((size%page.getPageSize()==0)?0:1));
			  if(page.getCurrentPage() > page.getTotalPage() || page.getCurrentPage() < 1)
				  page.setCurrentPage(1);
			  
			  criteria = session.createCriteria(Student.class);
			  if(page.isSerchByJob()){
				  Job job = new Job();
				  job.setId(page.getJobId());
				  criteria.add(Restrictions.eq("job",job));
			  }
			 
			  if(page.isSearchByName())
				 criteria.add(Restrictions.like("name","%"+page.getName()+"%"));
		      criteria.setFirstResult((page.getCurrentPage()-1)*page.getPageSize());
		      criteria.setMaxResults(page.getPageSize());
		      criteria.addOrder(Order.asc("name"));
		      list = (List<Student>)criteria.list();
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
	public boolean addStudent(Student student){
		 Session session = getSessionFactory().getCurrentSession();
		 Transaction  transaction = session.beginTransaction();
		 try{
			 Banji banji = (Banji)session.get(Banji.class, 1);
			 student.setBanji(banji);
			 student.setPassword(student.getStudentNum());
			 Integer id = (Integer)session.save(student);
			  if(id != null)
				  student.setId(id);
			  transaction.commit();
			  return true;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return false;
		}
	}
	//get by id;
	 public Student getStudentById(int id){
		 Session session = getSessionFactory().getCurrentSession();
		 Transaction  transaction = session.beginTransaction();
		 try{
	         Student student = (Student)session.get(Student.class, id);
	         transaction.commit();
			  return student;
		  }
		  catch (Exception ex) {
			  ex.printStackTrace();
			  transaction.rollback();
			  return null;
		}
		 
    }
	 //change job
	 public boolean changeStudentJob(int sd, int jd){
		 Session session = getSessionFactory().getCurrentSession();
		 Transaction  transaction = session.beginTransaction();
		 try{
	         Student student = (Student)session.get(Student.class, sd);
	         Job job = (Job)session.get(Job.class, jd);
	         if(job==null || student == null)
	        	 return false;
	         student.setJob(job);
	         session.update(student);
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
	 public boolean deleteStudent(int id){
		 Session session = getSessionFactory().getCurrentSession();
		 Transaction  transaction = session.beginTransaction();
		 try{
	         Student student = (Student)session.get(Student.class, id);
		     if(student==null)
		    	 return false;
	         session.delete(student);
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
	 //************************************************************************
}