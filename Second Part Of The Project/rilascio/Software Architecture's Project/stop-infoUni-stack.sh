#!/bin/bash

echo 'Removing infoUni application as a stack' 

source "docker.env"

docker stack rm infoUni-9012
