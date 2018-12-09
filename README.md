# 사용 라이브러리 및 툴 기타 사항
- docker : 서버 가상화 및 자동화
- docker-compose : 다중 서버 가상화 및 자동화 ( docker-compose up으로 모든 서버 실행 )
    - httpd(Apache) : 정적 웹서버 구동.
    - tomcat : 동적 WAS 서버
        - dockerize : MySQL 서버가 구동 될때까지 대기
    - mysql : 데이터 베이스 
- Spring boot - JPA를 이용해 Mysql 서버에서 데이터를 액세스 하고 이를 클라이언트에게 전달하는 api서버 역할을 함
    - 미세먼지 데이터 오픈 Api : 우리나라 미세먼지 데이터 조회를 위해 사용됨. API키가 필요합니다. docker-compose.yml 에서 'OPEN_API_KEY' 환경 변수로 변경 가능.

- Vue.js : 클라이언트 단 처리 목적
    - vue-cli 유틸 : Vue 프로젝트를 자동으로 생성해주는 유틸.
        - webpack / babel : *.vue 파일, js , html 등을 컴파일
    - d3.js : 우리나라 도시를 구분하는 맵을 그림
    - billboard.js : 도시별로 미세먼지 정보를 주별/일별/시간별 평균을 조회할 수 있음
    - kakao map api( Daum 맵 api ) : Geolocation을 이용해 현재 사용자가 살고 있는 곳의 좌표를 가져오고 카카오 맵 api를 이용해 현재 도시를 알아냄 , API 키 필요 


# 빌드 및 설치

도커로 따로 구동할 필요 없이 docker-compose를 통해서 한번에 구동 가능합니다.

( 프로젝트를 구동하기 위해서는 **docker** 와 **npm**, **python**이 있어야 합니다. )

[도커 설치](https://www.docker.com/get-started)

[노드 설치](https://nodejs.org)

[파이썬 설치](https://www.python.org)

도커와 노드, 파이썬을 설치 했으면, 

python setup.py로 설치를 바로 하실 수 있습니다.

기본적으로 80포트를 기준으로 서버가 열립니다. 

이를 수정하시려면 docker-compose.yml 에 httpd>ports에 80:80을 원하는포트:80 으로 바꿔주세요.

단, 포트를 바꾸면 kakao map api가 작동 안할 수 있습니다.

설치 후에는 http://localhost 혹은 http://127.0.0.1 로만 접속해주세요. 

```
$ python setup.py
```

setup.py는 다음과 같은 작업을 합니다.

1. client 폴더에서 npm install로 클라이언트 dependency 패키지 설치
2. npm run build로 vue 프로젝트 빌드 -> client\dist 폴더에 빌드된 파일들이 생성됨
3. server 폴더에서 gradlew build 수행 -> 서버 dependency 패키지 설치 및 war 생성
    - build\libs 에 api.war 생성
4. docker 이미지 안으로 빌드된 파일들을 넣기 위해 복사함
    - client\dist -> httpd\dist 로 복사됨
    - server\build\libs\api.war -> tomcat\api.war로 복사됨
5. docker-compose up --build 수행 ( 도커 이미지 생성 및 컨테이너 생성 )
    - tomcat, mysql, httpd 3개의 컨테이너가 생성됨
    - 각각 폴더에 있는 Dockerfile 을 기준으로 도커 이미지가 생성
    - tomcat의 경우 tomcat 설치 및 api.war을 deploy, dockerize 설치
    - httpd 의 경우 apache2 설치 후 tomcat 연결을 위한 환경 설정 파일 적용
    - mysql 의 경우 mysql 설치 후 기본 인코딩 설정 변경 (custom.cnf), initial.sql 수행( 컨테이너 실행시 데이터가 하나도 없는 경우 수행 )
