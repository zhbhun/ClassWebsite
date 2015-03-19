package test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.service.StudentBean;
import com.struts.action.StudentAction;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//StudentBean student = (StudentBean)ctx.getBean("studentBean");
		StudentAction students = (StudentAction)ctx.getBean("studentAction");
	//StudentAction students = new StudentAction();
		students.leader();
	}

}
