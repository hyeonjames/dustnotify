#!/bin/bash

echo "DB Server Wait"
dockerize -wait tcp://$MYSQL_NAME:3306 -timeout 40s

echo "TOMCAT START $CATALINA_HOME" 
catalina.sh run