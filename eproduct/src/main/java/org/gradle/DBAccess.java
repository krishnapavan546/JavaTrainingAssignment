package org.gradle;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.gmart.retailer.beans.ProductInfo;

import com.couchbase.client.ClusterManager;
import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.CouchbaseConnection;
import com.couchbase.client.CouchbaseConnectionFactory;
import com.couchbase.client.CouchbaseConnectionFactoryBuilder;
import com.couchbase.client.clustermanager.BucketType;
import com.couchbase.client.vbucket.provider.ConfigurationProvider;
import java.net.URI;
import java.net.URISyntaxException;

import net.spy.memcached.metrics.MetricType;

public class DBAccess {
	
	ProductInfo productInfo;
	
//	CouchbaseConnection couchbaseConnection=configurationProvider.getConfig();
	
	public CouchbaseClient connectDB()
	{
		CouchbaseClient couchbaseClient=null;
		try {
			/*ClusterManager cluster=new ClusterManager(Arrays.asList(new URI("http://127.0.0.1:8091/pools")),"productinfo","admin123");
			cluster.createNamedBucket(BucketType.MEMCACHED, "productinfo", 2054,1, "admin123",true);
			*/
			
			CouchbaseConnectionFactoryBuilder connectionFactoryBuilder=new 	CouchbaseConnectionFactoryBuilder();
			connectionFactoryBuilder.setEnableMetrics(MetricType.PERFORMANCE);
			CouchbaseConnectionFactory couchbaseConnectionFactory=connectionFactoryBuilder.buildCouchbaseConnection(Arrays.asList(new URI("http://127.0.0.1:8091/pools")),"productinfo","admin123");
			couchbaseClient=new CouchbaseClient(couchbaseConnectionFactory);
			//CouchbaseConnection couchbaseConnection=new CouchbaseConnection(20,couchbaseConnectionFactory,null,null,null,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return couchbaseClient;
	}
	public static void main(String[] args) {
		DBAccess dbAccess = new DBAccess();
		CouchbaseClient couchbaseClient=dbAccess.connectDB();
		/*//JSONObject jsonObject = null;
		try {
			String jsonString="{\"id\": 1000,\"productinfo\": {\"productname\": \"switch\",\"category\": \"electric\"}}";
			//jsonObject = new JSONObject(jsonString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//couchbaseClient.add("product_1001",);
*/	}

}
