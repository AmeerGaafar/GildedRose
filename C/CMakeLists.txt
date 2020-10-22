cmake_minimum_required(VERSION 3.9)
project(GRGTest)

set(CMAKE_CXX_STANDARD 11)

add_subdirectory(lib/gtest)

file(GLOB PROD_SRC "src/*.c")
file(GLOB TEST_SRC "test/*.cc")

add_executable(GRGEXE ${PROD_SRC})

add_executable(GRGTestExe ${TEST_SRC})

target_link_libraries(GRGEXE gtest gtest_main)

target_link_libraries(GRGTestExe gtest gtest_main)

include_directories(lib/gtest/googletest/include)
include_directories(lib/gtest/googlemock/include)



