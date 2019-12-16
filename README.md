# sept2019-java-tours-MMADirectory
Project 3 - Find a MMA Club next to you!

The objectif is to propose an interactive map and let the user choose where he want to search a club.

#How to install
Install mysql:
`sudo apt-get install mysql`

##Setup mysql
Create the database:
`CREATE DATABASE mmfcg`

Create the user:
`CREATE USER user_name@localhost IDENTIFIED BY user_password`

Give the user all grants:
`GRANT ALL ON mmfcg.* TO user_name@localhost`

##Others settings
Java version 8 or higher

Change the user_name and password in the application.properties' file

Now you can use the application !
