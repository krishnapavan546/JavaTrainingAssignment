package org.gmart.retailer.epricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages="org.gmart.retailer.epricing")
public class PriceApplication
{
    public static void main( String[] arguments )
    {
    	SpringApplication.run(PriceApplication.class, arguments);
    }
}
