# Android MVVM-CleanArchitecture : Challenge-Android

This repository contains a detailed challenger sample app that implements MVVM-CleanArchitecture using Dagger2, Room, RxJava and Android Data Binding using Kotlin programming language.

# Package Organization

![packageorganization](https://raw.githubusercontent.com/andre-couto/challenge-android/676720c89c3820a89a1d687d77cbd730532e6db2/Captura%20de%20Tela%202019-03-04%20a%CC%80s%2019.58.30.png)

# Clean Architecture

![clean](https://raw.githubusercontent.com/andre-couto/challenge-android/andre-couto-patch-1/clean_architecture.png)

# Architectural reactive approach

![architecturalreactiveapproach](https://camo.githubusercontent.com/d558d9e435ea64cbf0dfb44f0b3f2ace3605e711/687474703a2f2f726f636b6f2d626c6f672e71696e6975646e2e636f6d2f4d56564d5f416e64726f69642d436c65616e4172636869746563747572652d342e706e67)

# Local Development

Here are some useful Gradle/adb commands for executing this example:

- ./gradlew clean build - Build the entire example and execute unit and integration tests.
- ./gradlew installDebug - Install the debug apk on the current connected device.
- ./gradlew runUnitTests - Execute domain and data layer tests (both unit and integration).
- ./gradlew runAcceptanceTests - Execute espresso and instrumentation acceptance tests.

# Next Steps

- Pagination/RecyclerView Endless (Product List bu category ID)
- Local Storage/Cache
- Some Refactoring
- More Tests 

