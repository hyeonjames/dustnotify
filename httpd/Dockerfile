FROM httpd:2.4

MAINTAINER tmdgus0118@gmail.com
ENV TOMCAT_NAME team5_tomcat
RUN apt-get update \
  && apt-get install -y --no-install-recommends libapache2-mod-jk

ADD httpd.conf $HTTPD_PREFIX/conf/httpd.conf

ADD 000-default.conf $HTTPD_PREFIX/sites-enabled/000-default.conf

ADD worker.properties /etc/libapache2-mod-jk/workers.properties

ADD jk.conf /etc/libapache2-mod-jk/mods-available/jk.conf

COPY dist/ /var/www/html

EXPOSE 80 443

RUN mkdir -p /var/log/apache2
CMD ["apachectl", "-k", "start", "-DFOREGROUND"]
