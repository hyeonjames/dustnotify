FROM mysql:8
MAINTAINER tmdgus0118@gmail.com
ENV MYSQL_ROOT_PASSWORD a1234567@
ENV MYSQL_PORT 3306

COPY initial.sql /docker-entrypoint-initdb.d/initial.sql
ADD ./custom.cnf /etc/mysql/conf.d/custon.cnf