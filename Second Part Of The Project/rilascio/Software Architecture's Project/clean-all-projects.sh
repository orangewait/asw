#!/bin/bash

set -x

gradle clean -b EurekaService/build.gradle 
gradle clean -b CourseService/build.gradle 
gradle clean -b FacultyService/build.gradle
gradle clean -b UniversityService/build.gradle 
gradle clean -b InfoUniService/build.gradle 




