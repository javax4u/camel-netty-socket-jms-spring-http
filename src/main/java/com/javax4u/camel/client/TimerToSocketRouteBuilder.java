/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.javax4u.camel.client;

import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author vdoxx
 */
public class TimerToSocketRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {
        from("timer://simpleTimer?period=2000&repeatCount=5")//negative value of repeatCount is for forever
                .setBody().simple("** Current time is ${header.firedTime} ** ")
                .to("log:?level=DEBUG&showBody=true")
                .to("netty:tcp://localhost:4209?disconnect=true"); // .to("log:?level=DEBUG&showBody=true");
    }

}
