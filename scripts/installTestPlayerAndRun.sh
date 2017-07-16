#/bin/bash!
./gradlew installTestPlayer; adb shell am start -n dk.tv2.tv2play.debug/dk.tv2.tv2play.ui.activity.MainFragmentChangeActivity;