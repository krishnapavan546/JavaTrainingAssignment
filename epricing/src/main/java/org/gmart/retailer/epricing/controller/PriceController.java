package org.gmart.retailer.epricing.controller;


import javax.ws.rs.core.Response;
import java.util.*;

import org.gmart.retailer.epricing.dao.PriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;


@RestController
public class PriceController
{
	@Autowired
	private PriceDao priceDao;
	public List<JsonObject> productList;
	

	@RequestMapping(value="/price/{id}")
	public Response getPriceDetails(@PathVariable("id") String productId)
	{		
		List<JsonObject> productList=priceDao.getPrice(productId);
		if(productList.size()!=0)
		{
			JsonArray jsonArray=JsonArray.empty();
			for (JsonObject jsonObject : productList) {
				jsonArray.add(jsonObject);
			}		
			
			return Response.ok(productList.toString()).build();
		}
		else
		{
			return Response.noContent().build();
		}
		
	}
	
}
