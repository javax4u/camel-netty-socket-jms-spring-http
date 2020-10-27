package com.javax4u.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartSocketServerNetty {

    public static void main(String... args) throws Exception {

        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                "spring-camel-config.xml");
        CamelContext camelContext = SpringCamelContext.springCamelContext(appContext, false);

        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
        //camelContext.addComponent("jms",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));//working
        PooledConnectionFactory pooledConnectionFactory = (PooledConnectionFactory) appContext.getBean("pooledConnectionFactory");
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(pooledConnectionFactory));//working

        camelContext.addRoutes(new SocketToJmsRouteBuilder());
        camelContext.addRoutes(new JmsToHttpRouteBuilder());
        
        camelContext.start();
    }

}
