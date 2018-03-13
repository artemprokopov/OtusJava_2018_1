#!/usr/bin/env bash

TYPE_GC="-XX:+UseG1GC"
TYPE_PROG="-jar"
MAIN_FILE="L04-1-gc.jar"
MIN_MEM="-Xms256m"
MAX_MEM="-Xmx256m"

java ${TYPE_PROG} ${MIN_MEM} ${MAX_MEM} ${TYPE_GC} ${MAIN_FILE}