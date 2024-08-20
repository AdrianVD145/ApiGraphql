# Recursos
Para poder ejecutar la api se requieren de dos contenedores adicionales de docker:

Infinispan: docker run -it -p 11222:11222 -e USER="admin" -e PASS="password" quay.io/infinispan/server:15.0
Mysql: docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=Apigraphql123 -d mysql:latest

La api se esta probando con la herramienta POSTMAN

# Pasos
1.- Iniciar los contenedores de infinispan y mysql
2.- Se requiere una base de datos inicial, ejecutar el script Catalog, en nuestra Base de datos
3.- Ejecutar el siguiente Comando: "quarkus dev" en la consola

4.- Para poder acceder a las querys de graphql es necesario primero autenticarse con el endpoint Login:
https://github.com/AdrianVD145/ApiGraphql/blob/main/AssetsReadme/Login%20Postman.png

5.- Acceder a las querys de graphql
https://raw.githubusercontent.com/AdrianVD145/ApiGraphql/main/AssetsReadme/Querys Postman.png
https://github.com/AdrianVD145/ApiGraphql/blob/main/AssetsReadme/Querys%20Postman.png

# Notas

Si se quiere utilizar docker compose, existe un pequeño problema con las variables de entorno que se pasan como parametros en las properties
para solucionarlo, primero se compila el proyecto con las variables a utilizar en el docker compose con el comando:

 mvn -DskipTests=true package

despues comentamos las configuraciones inciales del properties y descomentamos las que utilizaremos para el docker compose:
https://github.com/AdrianVD145/ApiGraphql/blob/main/AssetsReadme/Nota%20properties.png

Despues ejecutamos los comandos:
docker compose build
docker compose up -d

# Warnings
para docker compose, Si el contenedor falla es posible que una de las posibles causas es que el inicio del servicio de infinispan no termino de cargarse, solo volver a correr el contenedor de la aplicacion




-------------------------------------------------------------------------------------------------------------------------------------------------------------

# ApiGraphql

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/ApiGraphql-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
