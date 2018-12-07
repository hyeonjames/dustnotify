#!/bin/bash

echo "DB Server Wait"
dockerize -wait tcp://sfw_mysql:3306 -timeout 20s

echo "TOMCAT START $CATALINA_HOME" 
catalina.sh run