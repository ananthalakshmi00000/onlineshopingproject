package com.niit.testcases;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserDAO;
import com.niit.model.UserInfo;


public class Usertestcase 
{
	@Autowired
	static UserDAO userDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	    
	    context.scan("com.niit");
	    context.refresh();
	    
	    userDAO=(UserDAO) context.getBean("userDAO");
	}
	@Ignore
	@Test
	public void addUserTest()
	{
		UserInfo user=new UserInfo();
		user.setUsername("rahul");
		user.setPassword("rahul@123");
		user.setRole("ADMIN");
		user.setEnabled(true);
		user.setCustomerName("Jhone");
		user.setCustomerAddr("10-233/2 Ameerpet  Hyderbad 500041");
		
		assertTrue("Problem in adding the user",userDAO.updateAddress(user));
	}
	
	
}
