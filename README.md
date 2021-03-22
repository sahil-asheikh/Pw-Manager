## Password-Manager
* A Password Manager is a Java application that encrypts the user's password and stores it into the Oracle Database and allows users to decrypt, retrieve and manage their local passwords with their master password in an Oracle Database for applications, websites, and online services.

## Getting Started
* You just have to download the file and import in any java/jdbc editor.

## Prerequisites
* You need install any JAVA editor, Oracle & Apache Tomcat and have to create table [create table emp (eno number(10), ename varchar2(2000), salary number(9999);]

## Built With
* This application is developed using Java, JDBC, Oracle.

## Tables has to created in database
* create table pwmanager_001 (id number(3), password varchar2(20), key number(3), username varchar2(20), user_email varchar2(40), app_name varchar2(20), url varchar2(30));
* create table userlogin_001 (id number(3), username varchar2(40), password varchar2(20), email varchar2(40));
