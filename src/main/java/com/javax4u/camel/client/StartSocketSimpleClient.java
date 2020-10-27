package com.javax4u.camel.client;

import java.io.DataOutputStream;
import java.net.Socket;

@Deprecated
public class StartSocketSimpleClient {

    public static void main(String argv[]) throws Exception {

        Socket clientSocket = new Socket("localhost", 4209);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        outToServer.writeBytes("Hello Camel\n");

        clientSocket.close();
    }
}