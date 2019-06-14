package com.niit.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.UserDAO;
import com.niit.model.Category;
import com.niit.model.UserInfo;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO 
{

	 @Autowired
	    SessionFactory sessionFactory;
	    
	public boolean addUser(UserInfo user) 
	{
		sessionFactory.getCurrentSession().save(user);
		return true;
		
	}

	public boolean registerUser(UserInfo user) 
	{
		sessionFactory.getCurrentSession().save(user);
		return true;
	}

	public boolean updateAddress(UserInfo user) {
		 sessionFactory.getCurrentSession().update(user);
	        return true;

	}

	public UserInfo getUser(String username) {
		Session session=sessionFactory.openSession();
        UserInfo user=session.get(UserInfo.class,username);        
		return user ;
	}

}
