# 사용 라이브러리 및 툴 기타 사항
- docker : 서버 가상화 및 자동화
- docker-compose : 다중 서버 가상화 및 자동화 ( docker-compose up으로 모든 서버 실행 )
    - httpd(Apache) : 정적 웹서버 구동.
    - tomcat : 동적 WAS 서버
    - mysql : 데이터 베이스 
- Spring boot - JPA를 이용해 Mysql데이터를 액세스 하고 이를 클라이언트에게 전달하는 api서버 역할을 함
    - 미세먼지 데이터 오픈 Api : 우리나라 미세먼지 데이터 조회를 위해 사용됨. API키가 필요합니다. application.properties 파일에 키를 설정할 수 있음.

- Vue.js : 클라이언트 단 처리 목적
    - vue-cli 유틸 : Vue 프로젝트를 자동으로 생성해주는 유틸.
    - webpack / babel : *.vue 파일, js , html 등을 컴파일
    - d3.js : 우리나라 도시를 구분하는 맵을 그림
    - billboard.js : 도시별로 미세먼지 정보를 주별/일별/시간별 평균을 조회할 수 있음
    - kakao map api( Daum 맵 api ) : Geolocation을 이용해 현재 사용자가 살고 있는 곳의 좌표를 가져오고 카카오 맵 api를 이용해 현재 도시를 알아냄 , API 키 필요
    - 