# 사용 라이브러리 및 툴 기타 사항
- docker : 서버 가상화 및 자동화
- docker-compose : 다중 서버 가상화 및 자동화 ( docker-compose up으로 모든 서버 실행 )
    - httpd(Apache) : 정적 웹서버 구동.
    - tomcat : 동적 WAS 서버
        - dockerize : MySQL 서버가 구동 될때까지 대기
    - mysql : 데이터 베이스 
- Spring boot - JPA를 이용해 Mysql데이터를 액세스 하고 이를 클라이언트에게 전달하는 api서버 역할을 함
    - 미세먼지 데이터 오픈 Api : 우리나라 미세먼지 데이터 조회를 위해 사용됨. API키가 필요합니다. application.properties 파일에 키를 설정할 수 있음.

- Vue.js : 클라이언트 단 처리 목적
    - vue-cli 유틸 : Vue 프로젝트를 자동으로 생성해주는 유틸.
    - webpack / babel : *.vue 파일, js , html 등을 컴파일
    - d3.js : 우리나라 도시를 구분하는 맵을 그림
    - billboard.js : 도시별로 미세먼지 정보를 주별/일별/시간별 평균을 조회할 수 있음
    - kakao map api( Daum 맵 api ) : Geolocation을 이용해 현재 사용자가 살고 있는 곳의 좌표를 가져오고 카카오 맵 api를 이용해 현재 도시를 알아냄 , API 키 필요 


# 빌드 및 설치

프로젝트를 구동하기 위해서는 **docker** 와 **npm**, **python**이 있어야 합니다.

[도커 설치](https://www.docker.com/get-started)
[노드 설치](https://nodejs.org)


도커와 노드를 설치 했으면, 
ubuntu, tomcat, mysql 도커 이미지를 설치합니다.
```
$ docker pull ubuntu:16:04
$ docker pull mysql:8
$ docker pull tomcat:8.5
```
 python setup.py로 설치를 합니다

```
python setup.py
```