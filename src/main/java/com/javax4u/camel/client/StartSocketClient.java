package com.javax4u.camel.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.jndi.JndiContext;

public class StartSocketClient {

    public static void main(String... args) {

        try {
            JndiContext jndiRegistry = new JndiContext();
            CamelContext camelContext = new DefaultCamelContext(jndiRegistry);

            camelContext.addRoutes(new TimerToSocketRouteBuilder());
            camelContext.start();
            int counter = 1;
            while (counter <= 6) {
                Thread.sleep(10000);//sleep for 10 seconds
                counter++;
            }
        //stop camel service after 60 seconds
            camelContext.stop();
        } catch (Exception ex) {
            Logger.getLogger(StartSocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
