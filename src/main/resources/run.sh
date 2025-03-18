#!/bin/bash

# Define variables
JDK_VERSION="jdk-11.0.26+4"
JDK_URL="https://github.com/adoptium/temurin11-binaries/releases/download/$JDK_VERSION/OpenJDK11U-debugimage_aarch64_linux_hotspot_11.0.26_4.tar.gz"
JAVA_ARCHIVE="java11.tar.gz"
JAVA_DIR="./java11"
JAR_URL="https://raw.githubusercontent.com/taraskovaliv/treads_test/refs/heads/master/src/main/resources/treads.jar"
JAR_FILE="treads.jar"

echo "Starting Java 11 download and setup..."

curl -L "$JAR_URL" -o "$JAR_FILE"
if [ ! -f "$JAR_FILE" ]; then
    echo "Error: JAR file '$JAR_FILE' not found in current directory."
    exit 1
fi

if [ ! -d "$JAVA_DIR" ]; then
    curl -L "$JDK_URL" -o "$JAVA_ARCHIVE"
    mkdir -p "$JAVA_DIR"
    tar -xzf "$JAVA_ARCHIVE" -C "$JAVA_DIR" --strip-components=1
    rm "$JAVA_ARCHIVE"
else
    echo "Found existing Java installation at $JAVA_DIR"
fi

echo "Running JAR file: $JAR_FILE"
"$JAVA_DIR/$JDK_VERSION/bin/java" -jar "$JAR_FILE"