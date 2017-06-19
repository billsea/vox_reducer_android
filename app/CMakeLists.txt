# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library(reducer SHARED
              src/main/cpp/audio_main.cpp
              ../../../iOS_Projects/Static_Libraries/common_dsp/common_dsp/filt.h
              ../../../iOS_Projects/Static_Libraries/common_dsp/common_dsp/filt.cpp
              ../../../iOS_Projects/Static_Libraries/common_dsp/common_dsp/center_reducer.h
             ../../../iOS_Projects/Static_Libraries/common_dsp/common_dsp/center_reducer.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

target_link_libraries(reducer
                      android
                      atomic
                      log
                      OpenSLES)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.



