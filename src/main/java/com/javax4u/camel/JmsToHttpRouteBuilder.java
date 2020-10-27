package com.javax4u.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
public class JmsToHttpRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        from("jms:queue:VdoxxQueue")
                .to("log:?level=DEBUG&showBody=true")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .to("http://localhost/html5/vdoxx/rfidscan/simulateRFIDScan.htm?mode=12");

    }
}