package com.niit.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.CategoryDAO;
import com.niit.model.Category;

@Controller
public class CategoryController {
    @Autowired
    CategoryDAO categoryDAO;
	
	
	@RequestMapping("/category")
	public String showCategory(Model m)
	{
		List<Category> listCategories=categoryDAO.listCategories();
		m.addAttribute("listCategories",listCategories);
		m.addAttribute("pageinfo","Manage Category");
		return "Category";
	}
	@RequestMapping(value="/AddCategory",method=RequestMethod.POST)
	public String addCategory(Model m,@RequestParam("cName")String categoryName,@RequestParam("cDesc")String categoryDesc)
	{
		Category category=new Category();
		category.setCategoryName(categoryName);
		category.setCategoryDesc(categoryDesc);
		categoryDAO.addCategory(category);
		
		List<Category> listCategories=categoryDAO.listCategories();
		m.addAttribute("listCategories", listCategories);
		
		m.addAttribute("pageinfo","Manage Category");
		return "Category";
	}
	
	@RequestMapping(value="/deleteCategory/{categoryID}")
	public String deleteCategory(Model m,@PathVariable("categoryID")int categoryID)
	{
		Category category=categoryDAO.getCategory(categoryID);
		categoryDAO.deleteCategory(category);
		
		m.addAttribute("pageinfo","Manage Category");
		return "Category";
	}
		@RequestMapping(value="/UpdateCategory",method=RequestMethod.POST)
	public String updateCategory(Model m,@RequestParam("cID")int categoryID,@RequestParam("cName")String categoryName,@RequestParam("cDesc")String categoryDesc)
	{
		Category category=categoryDAO.getCategory(categoryID);
		category.setCategoryName(categoryName);
		category.setCategoryDesc(categoryDesc);
		categoryDAO.updateCategory(category);
		
		List<Category> listCategories=categoryDAO.listCategories();
		m.addAttribute("listCategories", listCategories);
		
		m.addAttribute("pageinfo","Manage Category");
		return "Category";
	}
		@RequestMapping(value="/editCategory/{categoryID}")
		public String editCategory(Model m,@PathVariable("categoryID")int categoryID)
		{
			Category category=categoryDAO.getCategory(categoryID);
			
			m.addAttribute("category",category);
			m.addAttribute("pageinfo","Manage Category");
			return "UpdateCategory";
		}

}
