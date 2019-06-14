package com.niit.testcases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.ProductDAO;
import com.niit.model.Product;


public class Producttestcases 
{
	@Autowired
	static ProductDAO productDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	    
	    context.scan("com.niit");
	    context.refresh();
	    
	    productDAO=(ProductDAO) context.getBean("productDAO");
	}
	@Ignore
	@Test
	public void addProductTest()
	{
		Product product=new Product();
		product.setProName("Samsung Galaxy On NXT");
		product.setProDesc("Latest  Features");
		product.setProprice(25000);
		product.setStock(30);
		product.setCategoryID(111);
		product.setSupplierId(333);
		
		assertTrue("Problem in adding the product",productDAO.addProduct(product));
	}
	@Ignore
	@Test
	public void deleteProductTest()
	{
		Product product=productDAO.getProduct(2);
		assertTrue("Problem in deleting product",productDAO.deleteProduct(product));
	}
	@Ignore
	@Test
	public void updateProductTest()
	{
		Product product=productDAO.getProduct(2);

		product.setProName("Samsung Galaxy On NXT");
		product.setProDesc("Latest  Features");
		product.setProprice(25000);
		product.setStock(30);
		product.setCategoryID(111);
		product.setSupplierId(333);
		
		assertTrue("Problem in updating product",productDAO.updateProduct(product));
	}
	
	@Ignore
	@Test
		public void listAllCategoriesTestCase() {
			
		int actualSize = productDAO.listCategories().size();
			assertEquals(2, actualSize);
			
		}


}
