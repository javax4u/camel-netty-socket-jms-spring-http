@echo off
set VDOXX_HOME=c:\VirtualDoxx
set javadll=C:\Program Files\Java\jdk1.8.0_201\jre\bin\server\jvm.dll
set IS_JAVA_64_BIT=true
set outlog=%VDOXX_HOME%\RFIDListener2\stdout.log
set errlog=%VDOXX_HOME%\RFIDListener2\stderr.log

sc delete CamelSocketServer
rem del %VDOXX_HOME%\RFIDListener2\*.log
c:\VirtualDoxx\antcalls\JavaService_64_bit.exe -install CamelSocketServer "%javadll%" -Dlog4j.configurationFile=file:"log4j2.xml" -Djava.class.path=".;executable.jar" -start  com.javax4u.camel.StartSocketServerNetty -params future_use_1 future_use_2 -out "%outlog%" -err "%errlog%" -current "%VDOXX_HOME%\RFIDListener2"

