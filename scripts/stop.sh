#!/usr/bin/env bash


PROJECT_ROOT="/home/ec2-user/app/step1"
PROJECT_NAME=house-rent

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

JAR_NAME=$(ls -tr $REPOSITORY/ | grep *.jar | tail -n 1)

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_PID=$(pgrep -f ${PROJECT_NAME}*.jar)

# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_PID]; then
        echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다." >> $DEPLOY_LOG
else
        echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
        kill -15 $CURRENT_PID
fi