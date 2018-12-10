
import os
import build
build.build()
print('도커 시작')
if os.system('docker-compose up') != 0:
    print('docker compose failed. check if you have installed docker')
    exit(1)

