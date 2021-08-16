## Password-Manager
* A Password Manager is a Java application that encrypts the user's password and stores it into the MySQL Database and allows users to decrypt, retrieve and manage their local passwords with their master password in an MySQL Database for applications, websites, and online services.

## Getting Started
* You just have to clone the repo and import in any java/jdbc editor.

## Database and Tables
* Database: CREATE DATABASE passwordManager;

* Table: 	create table users(id int not null auto_increment, username varchar(20) not null, password varchar(20) not null, email varchar(50), primary key (id));
    
* Table: 	CREATE TABLE passwords(id int not null, userid int not null, password varchar(20), keyt int(3), username varchar(26), user_email varchar(30), app_name varchar(10), url varchar(30), primary key ( id ));

## Built With
* This application is developed using Java, JDBC, MySql.
