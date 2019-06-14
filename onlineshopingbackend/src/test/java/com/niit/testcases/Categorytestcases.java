package com.niit.testcases;
import static org.junit.Assert.*;


import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.CategoryDAO;
import com.niit.model.Category;



public class Categorytestcases 
{
	@Autowired
	static CategoryDAO categoryDAO;
	
	@BeforeClass
	public static void executeFirst()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	    
	    context.scan("com.niit");
	    context.refresh();
	    
	    categoryDAO=(CategoryDAO) context.getBean("categoryDAO");
	}
	@Ignore
	@Test
	public void addCategoryTest()
	{
		Category category=new Category();
		category.setCategoryName("Mobiles");
		category.setCategoryDesc("Good Features with all mobiles");
		
		assertTrue("Problem in adding the category",categoryDAO.addCategory(category));
	}
	@Ignore
	@Test
	public void deleteCategoryTest()
	{
		Category category=categoryDAO.getCategory(2);
		
		assertTrue("Problem in deleting category",categoryDAO.deleteCategory(category));
	}
	@Ignore
	@Test
	public void updateCategoryTest()
	{
		Category category=categoryDAO.getCategory(2);
		category.setCategoryName("Cloths");
		category.setCategoryDesc("Western Wear");
		
		assertTrue("Problem in updating category",categoryDAO.updateCategory(category));
	}
	
	
	
	@Ignore
	@Test
		public void listAllCategoriesTestCase() {
			
		int actualSize = categoryDAO.listCategories().size();
			assertEquals(2, actualSize);
			
		}


}
