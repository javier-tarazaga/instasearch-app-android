InstaSearch Application [![CircleCI](https://circleci.com/gh/javier-tarazaga/instasearch-app-android.svg?style=shield)](https://circleci.com/gh/javier-tarazaga/instasearch-app-android)
=========================
Simple app where people can see what Instagram post have been posted near their area.

As a result from the search, the app displays posts from the area. The search is by default to 500 meters around the users current location but the app allows the user to extend the search radius.

Changelog Version 1.0.0
--------
* First release of the application
* Follow Clean Architecture principles with a 3 tier layered architecture
* Added Dagger2 for DI
* Usage of RxJava and RxAndroid for composing asynchronous and event-based programs using observable sequences for the Java VM.
* Added multiple libraries to handle network, images, memory leaks, etc
* Possibility to login/logout from instagram
* Display latest posts from people around you given a certain maxDistance
* Possibility to change maxDistance from app preferences.
* Hardcoded Lat/Lng to Valencia for API data purposes.
* Added multiple tests for all layers
* Added integration with Fabric, HockeyApp and CircleCI
* Added automatic CD option to automatically release new versions to HockeyApp when pushed to Github
* Many other small details!

Systems in Use
--------
* HockeyApp for App Distribution (https://rink.hockeyapp.net/apps/c6720c02a0b943b588aac446fd995a34)
* CircleCI for CI and CD (https://circleci.com/gh/javier-tarazaga/instasearch-app-android)
* Fabric for analytics, insights and crash reporting (has many other features available) (https://fabric.io/home)

Future Improvements
--------
* Substitue the Client-Side (Implicit) Authentication method for the Server-side (Explicit) Flow.
* Make more extensive usage of Dagger2. Take a look at project [U2020](https://github.com/JakeWharton/u2020). Has a ton of useful features for both development and production. This project shows many really advanced techniques which will be really handy any project. This could be used per example to prepare and configure in more detail external libraries (Picasso, OkHttp, etc) according to scopes (global, perActivity, etc). 
* When API interaction gets more complex might be handy to add Retrofit to handle all that for us. 
* Add more tests. There is never enough tests so adding more will never harm. The app is pretty extensively tested right now but specially is lacking testing for the UI and flow around it (instrumental testing). Might be handy to use Roboelectric for some UI unitary testing as well. 
* Don't use Picasso the way I did it :) Simply expose an instance through Dagger as we can then configure it optimally in a global scope. 
* Of course, improve the UI and add a ton more of features which might be useful (Account management, search persistance, etc). 
* Make usage of Code Coverage tools with pre-commit hooks to make sure if the coverage drops belows certain %, don't allow to commit. 
* Make useage of pre-commit hooks to check for lint/code style errors (and even if you go really hardcore, to test with every commit)
* Fix the TODOs left in the app which are not relevant for the moment (BUT AN APP SHOULD BE ALWAYS TODO FREE!!!) 
* And many other things I can't think of right now but sure will come up in the future :D


Android-CleanArchitecture [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android--CleanArchitecture-brightgreen.svg?style=flat)](https://android-arsenal.com/details/3/909) 
=========================

This application is based in the Android Architecture example done by Fernando Cejas. For more information about the architecuture principles used in this application, please refer to the articles below.

[Architecting Android…The clean way?](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/)

[Architecting Android…The evolution](http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/)

[Tasting Dagger 2 on Android](http://fernandocejas.com/2015/04/11/tasting-dagger-2-on-android/)

[Clean Architecture…Dynamic Parameters in Use Cases](http://fernandocejas.com/2016/12/24/clean-architecture-dynamic-parameters-in-use-cases/)

[Demo video of this sample](http://youtu.be/XSjV4sG3ni0)

Clean architecture
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)

Architectural approach
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers.png)

Architectural reactive approach
-----------------
![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture_layers_details.png)

Local Development
-----------------

Here are some useful Gradle/adb commands for executing this example:

 * `./gradlew clean build` - Build the entire example and execute unit and integration tests plus lint check.
 * `./gradlew installDebug` - Install the debug apk on the current connected device.
 * `./gradlew runUnitTests` - Execute domain and data layer tests (both unit and integration).
 * `./gradlew runAcceptanceTests` - Execute espresso and instrumentation acceptance tests.
 
Code style
-----------

Here you can download and install the java codestyle.
https://github.com/android10/java-code-styles
