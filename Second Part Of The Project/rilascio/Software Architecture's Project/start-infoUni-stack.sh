#!/bin/bash

echo 'Starting infoUni application as a stack' 

source "docker.env"

docker stack deploy --compose-file docker-stack-infoUni.yml infoUni-9012
