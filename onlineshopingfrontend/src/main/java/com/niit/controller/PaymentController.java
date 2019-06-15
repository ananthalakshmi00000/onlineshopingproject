package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.*;

import com.niit.model.CartItem;
import com.niit.model.OrderDetail;
import com.niit.model.UserInfo;

@Controller
public class PaymentController 
{
	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	OrderDAO orderDAO;
	
	@RequestMapping("/checkout")
	public String checkout(Model m,HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		List<CartItem> cartItemList=cartDAO.listCartItems(username);
		
		m.addAttribute("cartItemList",cartItemList);
		m.addAttribute("totalAmount",this.getTotalAmount(cartItemList));
		
		String address=userDAO.getUser(username.trim()).getCustomerAddr();
		m.addAttribute("addr",address);
		
		return "OrderConfirm";
	}
	
	@RequestMapping(value="/updateAddress",method=RequestMethod.POST)
	public String updateAddress(@RequestParam("addr")String addr,Model m,HttpSession session)
	
	{
		String username=(String)session.getAttribute("username");
		List<CartItem> cartItemList=cartDAO.listCartItems(username);
		
		m.addAttribute("cartItemList",cartItemList);
		m.addAttribute("totalAmount",this.getTotalAmount(cartItemList));
		
		UserInfo userInfo=userDAO.getUser(username);
		userInfo.setCustomerAddr(addr);
		userDAO.updateAddress(userInfo);	
		
		String address=userInfo.getCustomerAddr();
		m.addAttribute("addr",address);
		return "OrderConfirm";
	}
	
	@RequestMapping(value="/payment")
	public String paymentPage(Model m,HttpSession session)
	{
		
		return "Payment";
	}
	
	@RequestMapping(value="/receipt",method=RequestMethod.POST)
	public String generateReceipt(@RequestParam("pmode")String pmode,Model m,HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		
		OrderDetail orderDetail=new OrderDetail();
		orderDetail.setOrderDate(new java.util.Date());
		orderDetail.setShippingAddr(userDAO.getUser(username).getCustomerAddr());
		orderDetail.setTranType(pmode);
		orderDetail.setUsername(username);
		
        List<CartItem> cartItemList=cartDAO.listCartItems(username);
		
		m.addAttribute("cartItemList",cartItemList);
		m.addAttribute("totalAmount",this.getTotalAmount(cartItemList));
		
		UserInfo userInfo=userDAO.getUser(username);
		
		orderDetail.setFinalAmount(this.getTotalAmount(cartDAO.listCartItems(username)));
		
		orderDAO.saveOrder(orderDetail);
		orderDAO.updateCart(username);
		
		m.addAttribute("orderDetail",orderDetail);
		
		
		return "Receipt";
	}
	public int getTotalAmount(List<CartItem> cartList)
	{
		int totalAmount=0,count=0;
		
		while(count<cartList.size())
		{
		  totalAmount=totalAmount+(cartList.get(count).getQuantity()*cartList.get(count).getProprice());
	      count++;		
		}
		return totalAmount;
	}

}
