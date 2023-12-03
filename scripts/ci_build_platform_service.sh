#!/bin/bash -e

mvn --projects 'platform-common,platform-app' clean compile package install -DskipTests=true
