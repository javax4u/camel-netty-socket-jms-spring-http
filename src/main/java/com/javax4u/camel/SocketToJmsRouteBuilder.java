package com.javax4u.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class SocketToJmsRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        // from("netty:tcp://localhost:4209?allowDefaultCodec=true&sync=false")
        //from("netty:tcp://localhost:4209?textline=false&sync=false")//not working no error
        //from("netty:tcp://localhost:4209?decoders=#length-decoder&sync=false")
        from("netty:tcp://localhost:4209?allowDefaultCodec=false&sync=false")//not working no error
                //.allowDefaultCodec
                .process(new MyProcessor())
                .to("log:?level=DEBUG&showBody=true")
                .to("jms:queue:VdoxxQueue");

    }

}

class MyProcessor implements Processor {

    public void process(Exchange exchange) throws Exception {
        Message socketData = exchange.getMessage();
        String stringMessage = socketData.getBody(String.class);
        System.out.println(stringMessage);
        exchange.getIn().setBody("RFID SCAN=" + stringMessage);

    }
}
