package org.gmart.retailer.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gmart.retailer.beans.ProductInfo;
import org.gmart.retailer.dao.ProductDAO;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource 
{
	
	/*Service is to Add product to couch base database and gives success or failure status back to client*/
	
	private ProductDAO productDAO=new ProductDAO();

	@POST
	@Path("/addition")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	public Response addProduct(ProductInfo productInfo)
	{
		try
		{
			productDAO = new ProductDAO();
			String status = (String) productDAO.addProduct(productInfo);
			if(status.equals("success"))
			{
				return Response.ok().build();
			}
			else
			{
				return Response.serverError().build();
			}			
		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

	@GET
	public Response getProducts()
	{
		List<JsonObject> productList=productDAO.getProducts();
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
	
	@GET
	@Path("/category/{producttype}")
	@Produces({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	public Response getProductByCategory(@PathParam("producttype")String category)
	{
		List<JsonObject> productList=productDAO.getProductsByCategory(category);
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
	
	@GET
	@Path("/product/{id}")
	public Response getProduct(@PathParam("id") int productId) {
		
		return Response.ok().build();
	}

	@DELETE
	@Path("/product/{id}")
	public Response deleteProduct(@PathParam("id") int productId) 
	{
		ProductDAO productDAO = new ProductDAO();
		productDAO.deleteProduct(productId);
		return Response.noContent().build();
	}
}
