package org.gmart.retailer.epricing.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
public class PriceBean 
{	

	private long price;

	public long getPrice() {
		return price;
	}

	public void setPrice(long price,long productid) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PriceBean [price=" + price + "]";
	}
	
}
