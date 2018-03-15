#!/bin/bash

VERSION=0.0.1-SNAPSHOT
CACHE_VERSION=v1

coursier bootstrap \
  au.com.agiledigital:gyoi_2.12:$VERSION \
  -f -o gyoi \
  -M au.com.agiledigital.gyoi.app.GrowYourOwnInfraApp \
  "$@"
