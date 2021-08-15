## Password-Manager
* A Password Manager is a Java application that encrypts the user's password and stores it into the Oracle Database and allows users to decrypt, retrieve and manage their local passwords with their master password in an Oracle Database for applications, websites, and online services.

## Getting Started
* You just have to clone the repo and import in any java/jdbc editor.

## Database and Tables
* Database: CREATE DATABASE passwordManager;

* Table: 	create table users (id int not null auto_increment, username varchar(20) not null, password varchar(20) not null, email varchar(50), primary key (id));
    
* Table: 	CREATE TABLE passwords (id INT(3) not NULL, password VARCHAR(20), 
	keyZ INT(3), username VARCHAR(26), user_email VARCHAR(30), app_name VARCHAR(10), url VARCHAR(30), PRIMARY KEY ( id ));

## Built With
* This application is developed using Java, JDBC, MySql.
