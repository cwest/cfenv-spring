#!/usr/bin/env bash

# Secure workaround for https://issues.sonatype.org/browse/MVNCENTRAL-1369
# Navigate to the root of your Spring Boot project where a Maven wrapper is present and run this script

cd .mvn/
wget https://gist.githubusercontent.com/kbastani/d4b4c92969ec5a22681bb3daa4a80343/raw/f166086ef051369383b02dfb74317cd07b6f2c6e/settings.xml

