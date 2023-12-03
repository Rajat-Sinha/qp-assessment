#!/bin/bash -e

SHA1=`git log --pretty=format:'%h' -n 1`
DATE=`date +"%Y-%m-%d-%H%M%S"`

# Tag for image should include SHA1 from which it was build and a timestamp
TAG=$SHA1-$DATE

# Change to directory having platform-app Dockerfiles
cd dist/docker/platform-app

docker build -t platform-app:$TAG .
