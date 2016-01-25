package org.gmart.retailer.resources;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gmart.retailer.beans.ProductInfo;
import org.gmart.retailer.dao.DAOInterface;
import org.gmart.retailer.dao.ProductDAO;
import org.gmart.retailer.services.ProductConfiguration;
import org.gradle.DBAccess;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource 
{
	
	@POST
	@Path("/append")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	public String addProduct(ProductInfo productInfo) 
	{
		try 
		{
			ProductDAO productDAO = new ProductDAO();
			String status = (String) productDAO.addProduct();
			return status;

		} 
		catch (Exception e) 
		{			
			e.printStackTrace();
			return "error";
		}
	}

	@GET
	@Path("/product/{id}")
	public Response getProduct(@PathParam("id") int productId) {
		
		return Response.ok().build();
	}

	@DELETE
	@Path("/product/{id}")
	public Response deleteReward(@PathParam("id") int productId) 
	{
		ProductDAO productDAO = new ProductDAO();
		productDAO.deleteProduct(productId);
		return Response.noContent().build();
	}
}
