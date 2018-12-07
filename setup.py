
import os
import shutil
os.chdir('client/')

if os.system('npm install') == 1:
    print("npm install failed. check if you've installed node package manager")
    exit(1)
if os.system('npm run build') == 1:
    print("npm run build failed.")
    exit(1)

os.chdir('../server')

if os.system('gradlew build') == 1:
    print('gradle build failed.')
    exit(1)
os.chdir('../')
shutil.copyfile('server/build/libs/api.war' , 'tomcat/api.war')


if os.system('docker-compose up --build') == 1:
    print('docker compose failed. check if you have installed docker')
    exit(1)

