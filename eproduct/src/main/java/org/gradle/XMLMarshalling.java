package org.gradle;
 
 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.gmart.retailer.beans.ProductInfo;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;


 
public class XMLMarshalling {
 
    private static final String FILE_NAME = "D:\\Pavan\\Code\\resources\\productInfo.xml";
 
    static Map<String,String> items=new HashMap<String,String>();
    
    
    
	public static void main(String[] args) 
	{
		items.put("1", "switch");
		ProductInfo order = new ProductInfo();
       /* order.setOrderId(1);
        order.setItems(items);*/
         
        jaxbObjectToXML(order);
        jaxbXMLToObject();
       
    }
	 private static ProductInfo jaxbXMLToObject() {
	        try {
	            JAXBContext context = JAXBContext.newInstance(ProductInfo.class);
	            Unmarshaller un = context.createUnmarshaller();
	            ProductInfo order = (ProductInfo) un.unmarshal(new File(FILE_NAME));
	            JsonObject jsonObject = null;
	            JsonArray jsonArray =null;
	            jsonArray.add(order);
	           // ((Map<String, String>) jsonObject).putAll(items);
	            System.out.println("here you see JSOn Array"+jsonObject);
	            System.out.println("here you see InquireEnterpriseOrderListResponseInfo Object"+order.toString());
	            return order;
	        } 
	        catch (JAXBException e)
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }
	
 
    private static void jaxbObjectToXML(ProductInfo emp) {
 
        try {
            JAXBContext context = JAXBContext.newInstance(ProductInfo.class);
            Marshaller m = context.createMarshaller();
            //for pretty-print XML in JAXB
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
            // Write to System.out for debugging
            // m.marshal(emp, System.out);
 
            // Write to File
            m.marshal(emp, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
 
}