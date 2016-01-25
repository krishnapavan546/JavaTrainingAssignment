package org.gmart.retailer.beans;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="product")
public class ProductInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String product;
	int productId;
	

	@XmlElement(name="product")
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	@XmlElement(name="productid")
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	
}
