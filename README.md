# sept2019-java-tours-MMADirectory
Project 3 - Find a MMA Club next to you!

The objectif is to propose an interactive map and let the user choose where he want to search a club.

# How to install
Install mysql:
`sudo apt-get install mysql;`

## Setup mysql
Create the database:
`CREATE DATABASE mfcg;`

Create the user:
`CREATE USER mfcg IDENTIFIED BY mfcg;`

Give the user all grants:
`GRANT ALL ON mfcg.* TO mfcg;`

## Others settings
Java version 8 or higher

Change the user_name and password in the application.properties' file

Now you can use the application !


--Application Properties--
// Informations sur la BDD à exploiter
// ajouter "?serverTimezone=GMT" à l'url est obligatoire pour MySQL 5
spring.datasource.url=jdbc:mysql://localhost:3306/mfcg?serverTimezone=GMT
spring.datasource.username=mfcg
spring.datasource.password=mfcg

// Autoriser l'affichage des requêtes SQL faites par Hibernate
spring.jpa.show-sql=true

// Gérer Hibernate
spring.jpa.hibernate.ddl-auto=update

// Sélectionner un "dialecte" permet à Hibernate de générer du SQL adapté à la version choisie
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB

// Templates reloading during development
spring.thymeleaf.prefix=file:src/main/resources/templates/
spring.thymeleaf.cache=false
// Static resources reloading during development
spring.resources.static-locations=file:src/main/resources/static/
spring.resources.cache.period=0
