EPL362 PROJECT
WEB SERVICES
Contributors: Evangelos Papadopoulos
              Marileni Angelidou
              Stella Couva
              Maria Elena Yianni

Description:
This project is implemented for academic purpose of class EPL362 and the subject
is the implemantation of a Legal Office using Web Services and 
Agile Methodologies.

Contents:
-A Dynamic Web Project that includes
 -6 packages
  -3 packages is Model,View,Controler for the Login and Register page
  -1 package: ws(4 web services)(Legal Staff,Legal Record Staff,
  Receptionist,Head office management)
  -1 package: wsclient(All Client Files including GUI and client Code)
  -1 package: tests where you can run junits for the testing of code
 -An ant buld.xml file
 -Code Conventions that followed
 -Javadoc for the Documentation

We have also use KanbanFlow for tasks of project

Instructions:
1.You must install the whole project.
2.You must include these jars for the project to run using build path on 
Referenced Libraries:
-com.mysql.jdbc_5.1.5.zip
-hamcrest-core-1.3.jar
-joda-time-2.4-javadoc.jar
-joda-time-2.4-sources.jar
-joda-time-2.4.jar
-juinit-4.12.jar
-mysql-connector-java-5.1.40-bin.jar
-swingx-1.6.jar
-jdatepicker 1.3.2.jar

3.You must run the 4 Web Services java classes from ws package.
 -legalStaffPublisher.java
 -LegalRecordStaffPublisher.java
 -ReceptionistPublisher.java
 -ManagementPublisher.java
 
4.1 You can run tests, by running TestRunner.java from tests package
4.2 You can start the application by running class APP.java from view package 

You can run the build by right clicking on it and run

5.In order to run the project is required to have a database install on 
localhost/phpmyadmin and a server running it with the credentials:
username:root
password:epapad0!

*1.To change credentials you have to go on ws package in MySQLAccess.java file
and change the credentials in every single connection with database.

*2.The build.xml is running succesful the project but because of the complexity
to run first the publishers you have to do the above procedure to run the 
project in a functional way.

