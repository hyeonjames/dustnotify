import os
import subprocess
print('환경 설정하는 중..')
os.environ["MYSQL_NAME"] = "localhost:3307"
os.environ["MYSQL_USER_NM"] = "root"
os.environ["MYSQL_USER_PW"] = "a1234567@"
os.environ["OPEN_API_KEY"] = r'Ik6zomYwmtDENJcCDgLVXlDTrXcjQ7JVm3EPLIQbPeO%2F%2B6W3h39mSASeNCRESjhUNp7jXLmVx5s7%2BkBMuSX5oQ%3D%3D'
print('도커 컨테이너 내리는 중..')
os.system("docker-compose down")
'''
print('MYSQL 서버 가동')
subprocess.Popen(["docker-compose","run","-p","3307:3306","db"])
'''
os.chdir("server")
print('서버 빌드 시작')
os.system("gradlew build")
print('서버 실행 시작')
os.system(["java","-jar","build\\libs\\api.war","-javaagent:./springloaded-1.2.8.RELEASE.jar","-noverify"])



