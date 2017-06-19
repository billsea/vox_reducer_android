#include <jni.h>
#include <string>
#include <memory>
#include <sys/types.h>
#include <SLES/OpenSLES.h>
#include "../../../../../../iOS_Projects/Static_Libraries/common_dsp/common_dsp/center_reducer.h"

struct ReducerAudioEngine {
    std::shared_ptr<center_reducer> reducer;
};
static ReducerAudioEngine engine;

extern "C" {
    //declaration
    JNIEXPORT jstring JNICALL
    Java_com_example_android_voxreducer_1android_MainActivity_stringFromJNI( JNIEnv *env, jobject /* this */);

    JNIEXPORT jboolean JNICALL
    Java_com_example_android_voxreducer_1android_MainActivity_createReducer(JNIEnv *env, jclass type);
}

//implementation
JNIEXPORT jstring JNICALL
Java_com_example_android_voxreducer_1android_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Vox Reducer";
    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT jboolean JNICALL
Java_com_example_android_voxreducer_1android_MainActivity_createReducer(JNIEnv *env, jclass type) {
    engine.reducer = std::make_shared<center_reducer>(44.1);
    return JNI_TRUE;
}