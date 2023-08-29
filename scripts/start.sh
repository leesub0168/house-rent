#!/usr/bin/env bash

PROJECT_ROOT="/home/ec2-user/app/step1"
REPOSITORY=/home/ec2-user/app/step1
PROJECT_NAME=house-rent

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > 빌드 파일 복사" >> $DEPLOY_LOG
cp $REPOSITORY/$PROJECT_NAME/build/libs/*.jar $REPOSITORY/

JAR_NAME=$(ls -tr $REPOSITORY/ | grep *.jar | tail -n 1)
# jar 파일 실행
echo "$TIME_NOW > $JAR_NAME 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_NAME > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_NAME)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG