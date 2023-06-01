#!/bin/bash

cd sehner-backend

#docker logout
#docker login

#docker-compose down
#docker build --cache-from sehnerbackend . -t sehnerbackend:latest
docker tag sehnerbackend:latest ansism/sehnerbackend:latest
docker tag sehnerfrontend:latest ansism/sehnerfrontend:latest
#docker push ansism/sehnerbackend:latest
#docker-compose up


