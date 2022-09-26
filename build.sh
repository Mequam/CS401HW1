#!/bin/sh

#get every .java file in the current directory
find . -name \*.java > to_build.txt

#compile
javac @to_build.txt