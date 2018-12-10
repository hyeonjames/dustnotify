# 사용 라이브러리 및 툴 기타 사항
- 과제 체크리스트
  - [x] docker 사용 - httpd, mysql, tomcat 폴더 내에 각각 Dockerfile 이 생성되어 있고 docker-compose로 빌드 및 구성
  - [x] Apache2 & Tomcat 연동 - httpd 이미지 생성할 때 mod_jk를 다운 받고 이를 적용해 톰켓과 연동함
  - [x] MySQL 연동 - mysql 8버전 사용
  - [x] Vue.js 사용
  - [x] Billboard.js 사용 - 차트 그리기
  - [x] D3.js 사용 - 대한민국 지도를 그릴때 사용

- **docker** : 서버 가상화 및 자동화
- docker-compose : 다중 서버 가상화 및 자동화 ( docker-compose up으로 모든 서버 실행 )
    - **httpd(Apache)** : 정적 웹서버 구동.
      - mod_jk로 **tomcat서버와 연동**합니다. 
      - **tomcat** 서버의 호스트 이름 (컨테이너 이름)은 TOMCAT_NAME 환경변수를 넘기면 됩니다. (docker-compose.yml 에서 설정)
    - **tomcat** : 동적 WAS 서버
      - dockerize : MySQL 서버가 구동 될때까지 대기
      - OPEN_API_KEY 환경변수는 [공공 데이터 포탈](https://www.data.go.kr/) 에서 받은 API키 입니다.
        - [대기오염 정보 조회 서비스](https://www.data.go.kr/dataset/15000581/openapi.do) 와 [측정소 정보 조회 서비스](https://www.data.go.kr/dataset/15000660/openapi.do?mypageFlag=Y) 가 필요합니다.
    - **mysql** : 데이터 베이스 
- **Spring boot** - JPA를 이용해 **Mysql 서버에서 데이터를 액세스** 하고 이를 클라이언트에게 전달하는 api서버 역할을 함
    - 미세먼지 데이터 오픈 Api : 우리나라 미세먼지 데이터 조회를 위해 사용됨. API키가 필요합니다. docker-compose.yml 에서 'OPEN_API_KEY' 환경 변수로 변경 가능.

- **Vue.js** : 클라이언트 단 처리 목적
    - vue-cli 유틸 : Vue 프로젝트를 자동으로 생성해주는 유틸.
        - webpack / babel : *.vue 파일, js , html 등을 컴파일
    - **d3.js** : 우리나라 도시를 구분하는 맵을 그림
    - **billboard.js** : 도시별로 미세먼지 정보를 주별/일별/시간별 평균을 조회할 수 있음
    - kakao map api( Daum 맵 api ) : Geolocation을 이용해 현재 사용자가 살고 있는 곳의 좌표를 가져오고 카카오 맵 api를 이용해 현재 도시를 알아냄 , API 키 필요 


# 테스트 서버 ( 실행 가능한 URL )
[여기](http://ec2-13-209-117-20.ap-northeast-2.compute.amazonaws.com) 에 접속하시면 됩니다.
AWS 프리티어 (micro 서버)를 사용하고 있기에 원활하지 않을 수 있습니다.

# 구성 환경 및 요구 사항 
- Windows 10 Pro 에서 됨을 확인 했습니다. Windows 10 Home 버전 Docker-Toolbox를 쓴 버전에서는 잘 되지 않을 수 있습니다. 
- Ubuntu 18.04에서 작동함을 확인 했습니다.
- 서버는 램을 어느정도 먹기에 최소 램 2GB 이상의 시스템을 권장 합니다.
- Chrome 에 최적화 되어 있습니다. d3 지도 맵 그리는게 조금 느릴 수 있음.

# 빌드 및 설치

도커로 따로 구동할 필요 없이 docker-compose를 통해서 한번에 구동 가능합니다.

( 프로젝트를 구동하기 위해서는 **docker** , **docker-compose**, **npm**, **java** **python**이 있어야 합니다. )

[도커 설치](https://www.docker.com/get-started)

[노드 설치](https://nodejs.org)

[파이썬 설치](https://www.python.org)

우분투 기준으로
```
$ curl -ssL get.docker.com | sh
$ sudo apt-get update
$ sudo apt-get install docker-compose nodejs npm default-jre python3
```

도커와 노드, 파이썬,자바를 설치 했으면, 

파이썬으로 setup.py를 실행하면 설치를 바로 하실 수 있습니다. ( 빌드만 하고 싶으실 경우 build.py만 실행해주세요. )

기본적으로 80포트를 기준으로 서버가 열립니다. 

이를 수정하시려면 docker-compose.yml 에 httpd>ports에 80:80을 원하는포트:80 으로 바꿔주세요.

단, 포트를 바꾸면 kakao map api가 작동 안할 수 있습니다. 


```
$ sudo python3 setup.py
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


# SERVER (Spring Boot)
- Spring Boot v.2.1

# 데몬 설정 ( 우분투 기준 )

build.py를 통하여 프로젝트 및 도커 빌드합니다.

```
$ sudo python build.py
```

dustdocker 파일을 /etc/init.d 에 옴겨주시고 chmod로 권한을 설정해주세요.

```
$ sudo cp ./dustdocker /etc/init.d/
$ sudo chmod 755 /etc/init.d/dustdocker
```

dustdocker 파일안에 있는 DUST_PATH 값을 프로젝트 폴더로 바꿔주세요 ( startup.sh 가 있는 폴더 )

그 다음은 update-rc.d 로 데몬을 설정해주면 됩니다.

```
$ sudo update-rc.d dustdocker defaults
```

아래처럼 service 명령어로 dustdocker를 start하면 서비스가 실행됩니다

```
$ sudo service dustdocker start
```
