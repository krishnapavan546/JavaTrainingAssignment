package org.gmart.retailer.services;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ProductConfiguration extends Configuration {
	
	
	String[] couchbaseNodes;
	
		
	@JsonProperty("couchbase.bucket")
	private String couchbaseBucket;

	@JsonProperty("couchbase.password")
	private String couchbasePassword;
	
	@JsonProperty("couchbase.username")
	private String couchBaseUserName;

	@JsonProperty("couchbase.nodes")
	public String[] getCouchbaseNodes() {
		return couchbaseNodes;
	}

	public String getCouchbaseBucket() {
		return couchbaseBucket;
	}

	public String getCouchbasePassword() {
		return couchbasePassword;
	}

	public String getCouchBaseUserName() {
		return couchBaseUserName;
	}
	
	


}
