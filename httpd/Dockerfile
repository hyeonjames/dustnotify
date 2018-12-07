FROM ubuntu:16.04

MAINTAINER tmdgus0118@gmail.com

RUN apt-get update && apt-get install -y --no-install-recommends apache2 libapache2-mod-jk

ADD apache2.conf /etc/apache2/apache2.conf

ADD 000-default.conf /etc/apache2/sites-enabled/000-default.conf

ADD worker.properties /etc/libapache2-mod-jk/workers.properties

ADD jk.conf /etc/apache2/mods-available/jk.conf

COPY dist/ /var/www/html

EXPOSE 80 443

CMD ["apachectl", "-k", "start", "-DFOREGROUND"]
