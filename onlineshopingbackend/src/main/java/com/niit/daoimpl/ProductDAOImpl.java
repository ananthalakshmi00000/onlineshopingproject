package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.ProductDAO;
import com.niit.model.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO
{

    @Autowired
    SessionFactory sessionFactory;
    
public ProductDAOImpl()
{
	
	System.out.println("Product DAO Impl Class is Initialized...........");
}
    
	public boolean addProduct(Product product) {
		
		sessionFactory.getCurrentSession().save(product);
		return true;
	}

	public boolean deleteProduct(Product product) 
	{
		sessionFactory.getCurrentSession().delete(product);
		return true;
	}

	public boolean updateProduct(Product product) 
	{
        sessionFactory.getCurrentSession().update(product);
        return true;

	}

	public List<Product> listCategories() {

		Session session=sessionFactory.openSession();
        Query query=session.createQuery("from Product");
        List<Product> listCategories=query.list();
        session.close();
		return listCategories;

	}

	public Product getProduct(int productID) 
	{
		Session session=sessionFactory.openSession();
        Product product=session.get(Product.class,productID);        
		return product ;

	}

}
