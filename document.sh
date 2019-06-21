#!/usr/bin/env bash

mkdir -p build/docs
find src -name "*.kt" | xargs grep -E 'PublishSubject.create' > build/docs/events.txt
