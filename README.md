# camel-netty-socket-jms
## Execution steps
This application has two main files. One to start server and another to start client.
Server part recieves message on socket port 4209 and route (redirect) it to jms. (==Make sure you have activemq installed on your system==)

Client part generates message with help of timer component, and sends it to socket port 4209 after each 2 seconds.

If you want to compile this repository then use build.bat file otherwise you can just run it.

Step-1: Download this repository and open two command prompt.
In first command prompt start server side by running run_server_first.bat file.
![run_server_first](image/run_server_first.png)

Step-2: In second command prompt start client side by running run_client_second.bat file.
![run_client_second](image/run_client_second.png)

#### Output
![client.png](image/client.png)
![server.png](image/server.png)
![activemq_1.png](image/activemq_1.png)
![activemq_2.png](image/activemq_2.png)
![activemq_3.png](image/activemq_3.png)

### connection pool setting
![conn_pool_config.png](image/conn_pool_config.png)
![activemq_openwire-multiple-connection.png](image/activemq_openwire-multiple-connection.png)

## Common-Errors and FAQ

### No component found with scheme: mina
org.apache.camel.ResolveEndpointFailedException: Failed to resolve endpoint: mina://tcp://localhost:4209?sync=false due to: No component found with scheme: mina
	
	Solution:add mina dependency in classpath

#### Q.Server name and port numbers are hard coded in this project how can externalize it.
	In next project you we will look into it.
	
#### Q.You have not configured connection pool for activemq to improve performance
	In next project you we will look into it.
	
#### Q. How can i install it as service?
	open cmd and goto release folder.
	change path according to your computer
	E:\camel-socket\release>InstallAsWindowsService.bat

#### Q.Does it required Activemq before installation as well as netbeans ? 
	Ans: It requires activemq only does not require netbeans or .net

#### Q. How  can i know activemq status, It is running or not?
	Ans: brwose http://localhost:8161/
	with user_name=admin password=admin
	if it shows no page than go to services of windows and search for ActiveMQ
	if it's there then start it otherwise download it from google
#### Q. What is the use of camel in this project ?
	Camel connects different componet of your project. Socket to JMS to http
	
### Flow Diagram
[Online Editor Link used to genrate below diagram with mermaid](https://mermaid-js.github.io/mermaid-live-editor)
```
graph LR
  1[1] --> 2{2}
  2 --> 2.1[2.1]
  2 --> 2.2[2.2]
	
graph LR
  K[socket] --> M{to}
  M --> N1[JMS]
  M --> N2[LOG]

graph LR
  X((computer)) 
  A((A. Socket:4929)) -->|from netty| B{B. to}
  B -->|log| C{C. log4j2}
  B -->|jms| D((D. AtiveMQ))
  C -->|RollingfileAppender| E[E. application.log]
  C -->|log|F[F. Console]
  D -->|POST| G((G. http))

graph LR
	A[jmsConnectionFactory] --> B(pooledConnectionFactory)
	B --> C1[JmsComponent]
	C1 --> D{CamelContext}
	C2[SocketToJmsRoute] --> D
	C3[JmsToHttpRoute] --> D
	
sequenceDiagram
  (~/\~) -->> Computer A: port_no:socket
  Computer A -->> Computer B: port_no:jms:ActiveMQ 
  Computer B -->> Computer A: port_no:jms:ActiveMQ 
  Computer A -->> Computer C: port_no:http:POST
	
```
### Flow Diagram
![Flow Diagram](image/flow_diagram.png)

### Object Diagram
![Object Diagram](image/object_diagram.png)

### Sequence Diagram
![Sequence Diagram](image/sequence_diagram.png)

### sl4j comparison with java.util.logging
![logging](image/logging.jpg)


