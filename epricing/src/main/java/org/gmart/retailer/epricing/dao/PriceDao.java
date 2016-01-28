package org.gmart.retailer.epricing.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;

@Service
public class PriceDao {
	
	private CouchbaseCluster cluster;
	private Bucket priceBucket;
	

	/*Takes Product id as input parameter and returns integer value of price...*/
	
	public List<JsonObject> getPrice(String productId) 
	{
		JsonObject jsonObject = null;
		List<JsonObject> result = null;
		try 
		{
			priceBucket = getCouchbaseBucket();
			/*jsonObject= priceBucket.get(productId).content();*/
			result = new ArrayList<JsonObject>();
			ViewResult viewResult = priceBucket.query(ViewQuery.from("dev_price_design_doc", "price_view"));
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
		}
		
		finally 
		{
			if (priceBucket != null && cluster!=null) 
			{
				priceBucket.close();
				cluster.disconnect();
			}
			else System.out.println("Connections were null");
		}
		return result;
	}

	private Bucket getCouchbaseBucket() 
	{
		cluster = CouchbaseCluster.create();
		return cluster.openBucket("priceinfo");

	}

}
