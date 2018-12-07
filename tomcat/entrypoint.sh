#!/bin/bash

echo "DB Server Wait"
dockerize -wait tcp://sfw_mysql:3306 -timeout 40s

echo "TOMCAT START $CATALINA_HOME" 
catalina.sh run