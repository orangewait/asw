#!/bin/bash

set -x

gradle build -b EurekaService/build.gradle 
gradle build -b CourseService/build.gradle 
gradle build -b FacultyService/build.gradle
gradle build -b UniversityService/build.gradle 
gradle build -b InfoUniService/build.gradle 




