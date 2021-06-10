#!/bin/bash

mkdir build
mkdir build/dist
javac -d build/ src/*.java
cd build
jar -cf  dist/humancalcs.jar *.class 
#generate manifest
jar -uvfe dist/humancalcs.jar test
