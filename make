#!/bin/bash
if [ "$1" == "clean" ]; then
    if [ -d build ]; then 
        rm -r build 
        echo "Tree cleanup completed!"
    else echo "Tree is already clean!"; fi
else
    echo "Making directories..."
    if [ -d build ]; then echo "Warning: build directory already exists"; fi
    [ -d build/dist ] || mkdir -p build/dist
    echo "Compiling sources..."
    javac -d build/ src/*.java
    cd build
    echo "Packing jarfile..."
    jar -cf  dist/humancalcs.jar *.class 
    #generate manifest
    jar -uvfe dist/humancalcs.jar test
    echo "jarfile created at build/dist/"
fi
