package org.gmart.retailer.services;

import org.gmart.retailer.resources.ProductResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ProductContext extends Application<ProductConfiguration> 
{
	public void initialize(Bootstrap<ProductConfiguration> bootstrap){}
	public void run(ProductConfiguration productConfiguration,Environment environment) throws Exception
	{
		environment.jersey().register(new ProductResource());		
	}
	public static void main(String[] args) throws Exception 
	{
		new ProductContext().run(args);
	}
}
