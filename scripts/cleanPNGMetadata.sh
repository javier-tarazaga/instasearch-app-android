#!/bin/sh
brew install exiftool
cd ..
find . -path '*src/**/res/*' -name '*.png' -exec exiftool -overwrite_original -all= {} \;