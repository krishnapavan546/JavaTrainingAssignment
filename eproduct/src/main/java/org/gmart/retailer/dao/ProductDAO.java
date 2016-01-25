package org.gmart.retailer.dao;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;

import org.gmart.retailer.beans.ProductInfo;
import org.gmart.retailer.exception.ProductException;
import org.gmart.retailer.services.ProductConfiguration;
import org.w3c.dom.NodeList;

import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.CouchbaseConnectionFactory;
import com.couchbase.client.CouchbaseConnectionFactoryBuilder;

public class ProductDAO implements DAOInterface {

	ProductDAO productDAO;
	CouchbaseClient couchbaseClient;
	static int productId=1003;

	public void setConnection() {
	}

	public CouchbaseClient getConnection() throws ProductException 
	{
		
		try {
			CouchbaseConnectionFactoryBuilder connectionFactoryBuilder = new CouchbaseConnectionFactoryBuilder();
			CouchbaseConnectionFactory couchbaseConnectionFactory = connectionFactoryBuilder
					.buildCouchbaseConnection(Arrays.asList(new URI("http://127.0.0.1:8091")), "productinfo", "");
			couchbaseClient = new CouchbaseClient(couchbaseConnectionFactory);
			if (couchbaseClient != null) 
			{
				return couchbaseClient;
			} 
			else 
			{
				throw new ProductException("couchbase client is null");
			}

		} 
		catch (IOException e)
		{
			throw new ProductException("couchbase client got with IO Exception"); 
			
		} 
		catch (URISyntaxException e) 
		{
			throw new ProductException("URI Exception");
		} 
	}

	public Object addProduct() 
	{
		try 
		{
			productDAO = new ProductDAO();
			if (productDAO != null)
			{
				couchbaseClient = productDAO.getConnection();
				String key=getKey();
				Object value=getValue(key);
				couchbaseClient.add(key,value);
			} 
			else 
			{
				System.out.println("ProductDAo is null");
				throw new ProductException("ProductDAO is null");
			}
		} 
		catch (ProductException e) 
		{
			e.printStackTrace();
			return "error";
		}

		return "200";
	}

	private Object getValue(String key) 
	{
		ProductInfo productInfo = new ProductInfo();
		if(key!=null)
		{
			
		}
		else
		{				
			productInfo.setProductId(Integer.parseInt(key));
		}
		return productInfo;
	}

	private String getKey() 
	{
		productId+=1;
		Integer productNumber = new Integer(productId);
		return productNumber.toString();
	}

	public boolean deleteProduct(int productId2) 
	{
		try 
		{
			productDAO = new ProductDAO();
			couchbaseClient = productDAO.getConnection();
		} catch (ProductException e)
		{
			
			e.printStackTrace();
		}
		finally
		{
			productDAO.disConnect();
		}
		return false;
	}

	private void disConnect() {
		// TODO Auto-generated method stub
		
	}

}
