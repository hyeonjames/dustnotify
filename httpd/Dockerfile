FROM httpd:2.4

MAINTAINER tmdgus0118@gmail.com

RUN apt-get update && apt-get install -y --no-install-recommends libapache2-mod-jk

ADD apache2.conf ${HTTPD_PREFIX}/apache2.conf

ADD 000-default.conf ${HTTPD_PREFIX}/sites-enabled/000-default.conf

ADD worker.properties /etc/libapache2-mod-jk/workers.properties

ADD jk.conf ${HTTPD_PREFIX}/mods-available/jk.conf

COPY dist/ /var/www/html

EXPOSE 80 443

CMD ["apachectl", "-k", "start", "-DFOREGROUND"]
