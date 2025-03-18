#!/bin/bash

# Define variables
JDK_URL="https://github.com/adoptium/temurin11-binaries/releases/download/jdk-11.0.26+4/OpenJDK11U-jdk_x64_linux_hotspot_11.0.26_4.tar.gz"
JAVA_ARCHIVE="java11.tar.gz"
JAVA_DIR="java11"
JAR_URL="https://raw.githubusercontent.com/taraskovaliv/treads_test/refs/heads/master/src/main/resources/treads.jar"
JAR_FILE="treads.jar"

echo "Starting Java 11 download and setup..."

curl -L "$JAR_URL" -o "$JAR_FILE"
if [ ! -f "$JAR_FILE" ]; then
    echo "Error: JAR file '$JAR_FILE' not found in current directory."
    exit 1
fi

curl -L "$JDK_URL" -o "$JAVA_ARCHIVE"
mkdir -p "$JAVA_DIR"
tar -xzf "$JAVA_ARCHIVE" -C "$JAVA_DIR" --strip-components=1

echo "Running JAR file: $JAR_FILE"
"$JAVA_DIR/bin/java" -cp "$JAR_FILE" dev.kovaliv.Main

rm "$JAVA_ARCHIVE"
rm -rf "$JAVA_DIR"
rm "$JAR_FILE"