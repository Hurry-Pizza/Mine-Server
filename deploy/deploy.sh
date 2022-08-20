#!/bin/sh

REPOSITORY=/home/ec2-user/docker
cd $REPOSITORY

APP_NAME=mine

docker stop $(docker ps -a | grep $APP_NAME | awk '{ print $1 }')
docker rm $(docker ps -a | grep $APP_NAME | awk '{ print $1 }')
docker-compose up -d