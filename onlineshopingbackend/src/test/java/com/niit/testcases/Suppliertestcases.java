package com.niit.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.SupplierDAO;
import com.niit.model.Supplier;

public class Suppliertestcases {
	@Autowired
	static SupplierDAO supplierDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	    
	    context.scan("com.niit");
	    context.refresh();
	    
	    supplierDAO=(SupplierDAO) context.getBean("supplierDAO");
	}
	@Ignore
	@Test
	public void addSupplierTest()
	{
		Supplier supplier=new Supplier();
		supplier.setSupplierName("James");
		supplier.setSupplierDesc("10-232/3 Hyderabad");
		
		assertTrue("Problem in adding the supplier",supplierDAO.addSupplier(supplier));
	}
	@Ignore
	@Test
	public void deleteSupplierTest()
	{
		Supplier supplier=supplierDAO.getSupplier(2);
		
		assertTrue("Problem in deleting supplier",supplierDAO.deleteSupplier(supplier));
	}
	@Ignore
	@Test
	public void updateSupplierTest()
	{
		Supplier supplier=supplierDAO.getSupplier(2);
		supplier.setSupplierName("Cloths");
		supplier.setSupplierDesc("Western Wear");
		
		assertTrue("Problem in updating supplier",supplierDAO.updateSupplier(supplier));
	}
	
	
	
	@Ignore
	@Test
		public void listAllCategoriesTestCase() {
			
		int actualSize = supplierDAO.listSuppliers().size();
			assertEquals(2, actualSize);
			
		}

}
