package com.niit.model;

import javax.persistence.*;

@Entity
@Table
public class Product {

	@Id
	@GeneratedValue

	int proId;
	String proName;
	String proDesc;
	int proprice;
	int stock;
	
	//@ManyToOne
	int categoryID;

	//@ManyToOne
	int supplierId;

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public int getProprice() {
		return proprice;
	}

	public void setProprice(int proprice) {
		this.proprice = proprice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

}
