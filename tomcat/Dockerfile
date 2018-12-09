FROM tomcat:8.5

MAINTAINER tmdgus0118@gmail.com

# add context to /usr/local/tomcat/webapps
# COPY ./sample.war /usr/local/tomcat/webapps/sample.war
ENV TZ 'Asia/Seoul'
COPY api.war $CATALINA_HOME/webapps/api.war
ENV DOCKERIZE_VERSION v0.4.0  
#RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \  
COPY dockerize-linux-amd64-v0.6.1.tar.gz /usr/local/tomcat/dockerize.tar.gz
RUN tar -C /usr/local/bin -xzvf dockerize.tar.gz


COPY entrypoint.sh ./entrypoint_org.sh

RUN tr -d '\r' < entrypoint_org.sh > entrypoint.sh
EXPOSE 8080
RUN chmod +x ./entrypoint.sh 
ENTRYPOINT [ "./entrypoint.sh" ]

# 소스파일 업로드

#ADD ../clients/dist /usr/local/tomcat/webapps/ROOT/
EXPOSE 8009
