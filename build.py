
# -*- encoding=utf-8 -*-

import os
from platform import platform
import shutil
def build():
    os.chdir('client/')
    print('클라이언트 dependency 설치')
    if os.system('npm install') != 0:
        print("npm install failed. check if you've installed node package manager")
        exit(1)

    print('클라이언트 빌드 시작')
    if os.system('npm run build') != 0:
        print("npm run build failed.")
        exit(1)

    os.chdir('../server')

    print('서버 빌드 시작')
    os.chdir('../server')
    if platform().lower().find('windows') >= 0:
        res = os.system('gradlew build')
    else:
        res = os.system('sh ./gradlew build')
    if res != 0:
        print('gradle build failed.')
        exit(1)
    os.chdir('../')

    print('서버 및 클라이언트 옴기는 중')
    if os.path.exists('httpd/dist'):
        shutil.rmtree('httpd/dist')
    shutil.copyfile('server/build/libs/api.war' , 'tomcat/api.war')
    shutil.copytree('client/dist', 'httpd/dist')
    print('도커 내리는 중')
    if os.system('docker-compose down') != 0:
        print('docker compose down failed. check if you have installed docker')
        exit(1)
    print('도커 빌드중')
    if os.system('docker-compose build') != 0:
        print('docker compose failed. check if you have installed docker')
        exit(1)
if __name__ == "__main__":
    build()