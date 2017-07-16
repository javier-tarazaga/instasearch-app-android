#/bin/bash!
./gradlew installRelease; adb shell am start -n dk.tv2.tv2play/dk.tv2.tv2play.ui.activity.MainFragmentChangeActivity;