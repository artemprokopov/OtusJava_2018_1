@echo off

set TYPE_GC=-XX:+UseParallelGC
set TYPE_PROG=-jar
set MAIN_FILE=L04-1-gc.jar
set MIN_MEM=-Xms256m
set MAX_MEM=-Xmx256m

java %TYPE_PROG% %MIN_MEM% %MAX_MEM% %TYPE_GC% %MAIN_FILE%