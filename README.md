# cxf-soaphandler-demo

## Environment

* CXF: 3.1.6
* Tomcat: 8.0.35
* OpenJDK: java-1.8.0-openjdk-1.8.0.92-1.b14.fc23.x86_64

## Usage

### Create server app and deploy to Tomcat

``` sh
$ ./mvnw clean package -pl server
$ cp server/target/cxf-soaphandler-demo-server.war <YOUR_CATALINA_HOME>/webapps
```

And run tomcat with 8080 port.

### Access to service

``` sh
$ ./mvnw clean test -pl client
```
