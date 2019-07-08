#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_techflake_NativeCaller_getGiphyBaseUrl(JNIEnv *env, jobject) {
  std::string news = "http://api.giphy.com/";
    return env->NewStringUTF(news.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_techflake_NativeCaller_getGiphyUrlPrefix(JNIEnv *env, jobject) {
  std::string news = "v1/gifs/search?q=";
    return env->NewStringUTF(news.c_str());
}


extern "C"
JNIEXPORT jstring
JNICALL
Java_com_techflake_NativeCaller_getGiphyUrlSuffix(JNIEnv *env, jobject) {
     std::string domain = "&api_key=fiQVpagM8Ua4ZwE72TIiuuKPCPO2cPcy&limit=10";
    return env->NewStringUTF(domain.c_str());
}

extern "C"
JNIEXPORT jstring
JNICALL
Java_com_techflake_NativeCaller_getGiphyTrendingVideoUrl(JNIEnv *env, jobject) {
     std::string domain = "v1/gifs/trending?q=cat&api_key=fiQVpagM8Ua4ZwE72TIiuuKPCPO2cPcy&limit=10";
    return env->NewStringUTF(domain.c_str());
}

