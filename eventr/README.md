# Simple CRUD Demo

A simple CRUD application using Java EE (JPA, JAX-RS, CDI etc.), based on top of Thorntail and MySQL.
Used as an example for streaming changes out of a database using Debezium.

To run the app, follow these steps:

    mvn clean package
    docker-compose up --build

Then visit the application in a browser at http://localhost:8079/.

## Deployment on OpenShift

    # Start MySQL (using Debezium's example image)
    oc new-app --name=mysql debezium/example-mysql:0.8
    oc env dc/mysql MYSQL_ROOT_PASSWORD=debezium  MYSQL_USER=mysqluser MYSQL_PASSWORD=mysqlpw

    # Build a WildFly image with the application
    oc new-app --name=myapp wildfly~https://github.com/debezium/microservices-lab.git \
        --context-dir=eventr \
        -e MYSQL_DATABASE=inventory \
        -e MYSQL_PASSWORD=mysqlpw \
        -e MYSQL_USER=mysqluser \
        -e MYSQL_DATASOURCE=OrderDS
    oc expose svc myapp

Then visit the application in a browser at http://myapp-myproject.<OS_IP>.nip.io/.
