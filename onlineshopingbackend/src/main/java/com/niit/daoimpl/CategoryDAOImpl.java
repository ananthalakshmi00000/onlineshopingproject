package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.CategoryDAO;
import com.niit.model.Category;

@Repository("categoryDAO")
@Transactional

public class CategoryDAOImpl implements CategoryDAO 
{


    @Autowired
    SessionFactory sessionFactory;
    
public CategoryDAOImpl()
{
	
	System.out.println("Category DAO Impl Class is Initialized...........");
}
    
	public boolean addCategory(Category category) {
		
		sessionFactory.getCurrentSession().save(category);
		return true;
	}

	public boolean deleteCategory(Category category) 
	{
		sessionFactory.getCurrentSession().delete(category);
		return true;
	}

	public boolean updateCategory(Category category) 
	{
        sessionFactory.getCurrentSession().update(category);
        return true;

	}

	public List<Category> listCategories() {

		Session session=sessionFactory.openSession();
        Query query=session.createQuery("from Category");
        List<Category> listCategories=query.list();
        session.close();
		return listCategories;

	}

	public Category getCategory(int categoryID) 
	{
		Session session=sessionFactory.openSession();
        Category category=session.get(Category.class,categoryID);        
		return category ;

	}

}
