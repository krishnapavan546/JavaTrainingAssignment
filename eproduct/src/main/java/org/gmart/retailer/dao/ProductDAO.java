package org.gmart.retailer.dao;

import java.util.ArrayList;
import java.util.List;

import org.gmart.retailer.beans.ProductInfo;
import org.gmart.retailer.exception.ProductException;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.PersistTo;
import com.couchbase.client.java.document.Document;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.QueryResult;
import com.couchbase.client.java.query.QueryRow;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;

import rx.Observable;

public class ProductDAO implements DAOInterface {
	ProductDAO productDAO;
	private static int productId = 1003;
	private CouchbaseCluster cluster;
	private Bucket bucket;
	private ProductInfo productInfo;

	
	public void setConnection() {
	}

	public Bucket getConnection() throws ProductException {

		try {
			cluster = CouchbaseCluster.create();
			return cluster.openBucket("productinfo");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bucket;
	}

	public Object addProduct(ProductInfo productInfo) {
		try {
			bucket = getConnection();
			System.out.println("got connection");
			JsonObject jsonObject=parseObject(productInfo);
			System.out.println("got JsonObject" +jsonObject);
			JsonDocument jDocument=JsonDocument.create(getKey(productInfo.getProductId()), jsonObject);
			System.out.println("got Document"+jDocument);
			bucket.async().upsert(jDocument);
			} catch (ProductException e) {
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	

	private Object getValue(String key) {
		ProductInfo productInfo = new ProductInfo();
		if (key != null) {

		} else {
			productInfo.setProductId(Integer.parseInt(key));
		}
		return productInfo;
	}

	private String getKey(int productId) {
		Integer productNumber = new Integer(productId);
		return productNumber.toString();
	}

	public boolean deleteProduct(int productId2) {
		try {

			bucket = getConnection();

		} catch (ProductException e) {

			e.printStackTrace();
		} finally {
			disConnect();
		}
		return false;
	}

	private void disConnect() {
		// TODO Auto-generated method stub

	}

	public List<JsonObject> getProductsByCategory(String category) {
		ArrayList<JsonObject> result = null;
		try {
			result = new ArrayList<JsonObject>();
			bucket = getConnection();
			ViewResult viewResult = bucket.query(ViewQuery.from("dev_product_design_doc", "category").key(category));
			for (ViewRow viewRow : viewResult.allRows()) {
				JsonDocument jsonDocument=viewRow.document();			
				result.add(jsonDocument.content());
				System.out.println("showing result of viewrow "+viewRow.toString()+"count receved is "+viewResult.allRows().size());
			}
		}
		catch (ProductException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	public List<JsonObject> getProducts() {
		ArrayList<JsonObject> result = null;
		try {
			result = new ArrayList<JsonObject>();
			bucket = getConnection();
			ViewResult viewResult = bucket.query(ViewQuery.from("dev_product_design_doc", "product_view").limit(10));
			if(viewResult.allRows().size()!=0)
			{
			for (ViewRow viewRow : viewResult.allRows()) {
				JsonDocument jsonDocument=viewRow.document();	
				if(jsonDocument!=null)
				{
				result.add(jsonDocument.content());
				}
				else
				{
					
				}
				System.out.println("showing result of viewrow "+viewRow.toString()+"count receved is "+viewResult.allRows().size());
			}
			}
			else
			{
				System.out.println("No results found in getproducts");
			}
		}
		catch (ProductException e1) {
			e1.printStackTrace();
		}
		return result;	

	}
	private JsonObject parseObject(ProductInfo productInfo) {		
		this.productInfo=productInfo;
		JsonObject json=JsonObject.empty().put(getKey(productInfo.getProductId()), productInfo.getProduct());
		return json;
	}

	private Bucket getCouchbaseBucket() {
		System.setProperty("com.couchbase.queryEnabled", "true");
		cluster = CouchbaseCluster.create();
		return cluster.openBucket("productinfo");

	}

}
