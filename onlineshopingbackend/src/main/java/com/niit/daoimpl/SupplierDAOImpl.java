package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.SupplierDAO;
import com.niit.model.Supplier;

@Repository("supplierDAO")
@Transactional
public class SupplierDAOImpl implements SupplierDAO 
{
	@Autowired
    SessionFactory sessionFactory;
    
public SupplierDAOImpl()
{
	
	System.out.println("Supplier DAO Impl Class is Initialized...........");
}
    
	public boolean addSupplier(Supplier supplier) {
		
		sessionFactory.getCurrentSession().save(supplier);
		return true;
	}

	public boolean deleteSupplier(Supplier supplier) 
	{
		sessionFactory.getCurrentSession().delete(supplier);
		return true;
	}

	public boolean updateSupplier(Supplier supplier) 
	{
        sessionFactory.getCurrentSession().update(supplier);
        return true;

	}

	public List<Supplier> listCategories() {

		Session session=sessionFactory.openSession();
        Query query=session.createQuery("from Supplier");
        List<Supplier> listCategories=query.list();
        session.close();
		return listCategories;

	}

	public Supplier getSupplier(int supplierID) 
	{
		Session session=sessionFactory.openSession();
        Supplier supplier=session.get(Supplier.class,supplierID);        
		return supplier ;

	}

}
