#!/bin/bash
releaseVersion=$(curl -H "X-HockeyAppToken: 5bd88f774e5f41e9ad40f66d28926d01" https://rink.hockeyapp.net/api/2/apps/c6720c02a0b943b588aac446fd995a34/app_versions | tr ',' '\n' | head -n 1 | tr '[' '\n' | grep '{"version":' | cut -b 13- | rev | cut -b 2- | rev)
developVersion=$(curl -H "X-HockeyAppToken: 5bd88f774e5f41e9ad40f66d28926d01" https://rink.hockeyapp.net/api/2/apps/c6720c02a0b943b588aac446fd995a34/app_versions | tr ',' '\n' | head -n 1 | tr '[' '\n' | grep '{"version":' | cut -b 13- | rev | cut -b 2- | rev)

nothingToDo="The version of the application is not greater than the one in HockeyApp. Nothing to do."

echo "Current version on hockey app is: $"
echo "Current app version is: $1"
echo "Current type of teh application: $2"

if [ $2 = "debug" ]
then
    if [ $developVersion -lt $1 ]
    then
        ../gradlew uploadDebugToHockeyApp
    else
        echo $nothingToDo
    fi
elif [ $releaseVersion -lt $1 ]
then
    ../gradlew uploadReleaseToHockeyApp
else
    echo $nothingToDo
fi






