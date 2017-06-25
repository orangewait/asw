#!/bin/bash

# Script per avviare il servizio infoUni 

echo Running InfoUni 

java -Xms64m -Xmx128m -jar build/libs/InfoUniService-0.0.1-SNAPSHOT.jar

