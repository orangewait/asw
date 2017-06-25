#!/bin/bash

source "docker.env"

# DOCKER_REGISTRY=localhost:5000
DOCKER_REGISTRY=swarm.inf.uniroma3.it:5000

docker push ${DOCKER_REGISTRY}/eureka-server-9012
docker push ${DOCKER_REGISTRY}/course-9012
docker push ${DOCKER_REGISTRY}/faculty-9012
docker push ${DOCKER_REGISTRY}/university-9012
docker push ${DOCKER_REGISTRY}/infouni-9012






