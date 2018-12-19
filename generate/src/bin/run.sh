#!/bin/bash
SHELL_FOLDER=$(cd "$(dirname "$0")";pwd)
echo $SHELL_FOLDER
cd "$SHELL_FOLDER"
cd ..
java -jar generate-jar-with-dependencies.jar