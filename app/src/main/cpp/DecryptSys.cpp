#include <jni.h>
#include <strings.h>
#include "tfhe.h"
#include "tfhe_io.h"
#include "tfhe_core.h"
#include <sys/sendfile.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <fstream>
#include <iostream>
#include <cmath>
#include <ctime>
#include <android/log.h>
#include <cstdlib>
#define  LOG_TAG    "Decryption:"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT jint JNICALL
Java_com_loginid_cryptodid_claimVerifier_Verifier_Decrypt(JNIEnv *env, jobject thiz, jstring ClaimPath,jstring SkPath) {

    const char *Claim_Path = env->GetStringUTFChars(ClaimPath, nullptr);
    const char *SK_Path = env->GetStringUTFChars(SkPath, nullptr);

    LOGD("Secret key path is : %s",SK_Path);
    LOGD("Claim path is : %s",Claim_Path);
    FILE* secret_key = fopen(SK_Path,"rb");
    TFheGateBootstrappingSecretKeySet* keyset = new_tfheGateBootstrappingSecretKeySet_fromFile(secret_key);

    LweSample* answer = new_gate_bootstrapping_ciphertext_array(16,keyset->params);
    FILE * claim_data = fopen(Claim_Path,"rb");

    for (int i=0; i<16; i++)
        import_gate_bootstrapping_ciphertext_fromFile(claim_data, &answer[i], keyset->params);
    fclose(claim_data);

    int16_t int_answer = 0;
    int ai[16];
    for (int i=0; i<16; i++) {
        ai[i] = bootsSymDecrypt(&answer[i], keyset)>0;
        int_answer |= (ai[i]<<i);
    }
    LOGD("answer is : %d",int_answer);
    return int_answer;
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_loginid_cryptodid_claimVerifier_Verifier_decryptVotingResult(JNIEnv *env, jobject thiz,
                                                                      jstring claim_path,
                                                                      jstring sk__path, jint nbit) {
    const char *Claim_Path = env->GetStringUTFChars(claim_path, nullptr);
    const char *SK_Path = env->GetStringUTFChars(sk__path, nullptr);

    LOGD("Secret key path is : %s",SK_Path);
    LOGD("Claim path is : %s",Claim_Path);
    FILE* secret_key = fopen(SK_Path,"rb");
    TFheGateBootstrappingSecretKeySet* keyset = new_tfheGateBootstrappingSecretKeySet_fromFile(secret_key);

    LweSample* answer = new_gate_bootstrapping_ciphertext_array(nbit,keyset->params);
    FILE * claim_data = fopen(Claim_Path,"rb");

    for (int i=0; i<nbit; i++)
        import_gate_bootstrapping_ciphertext_fromFile(claim_data, &answer[i], keyset->params);
    fclose(claim_data);

    int16_t int_answer = 0;
    int ai[16];
    for (int i=0; i<nbit; i++) {
        ai[i] = bootsSymDecrypt(&answer[i], keyset)>0;
        int_answer |= (ai[i]<<i);
    }
    LOGD("answer is : %d",int_answer);
    return int_answer;
}
extern "C"
JNIEXPORT jint JNICALL
Java_com_loginid_cryptodid_claimVerifier_Verifier_decryptAccessControlResult(JNIEnv *env,
                                                                             jobject thiz,
                                                                             jstring ClaimPath,
                                                                             jstring SkPath) {
    // TODO: implement decryptAccessControlResult()
    const char *Claim_Path = env->GetStringUTFChars(ClaimPath, nullptr);
    const char *SK_Path = env->GetStringUTFChars(SkPath, nullptr);

    LOGD("Secret key path is : %s",SK_Path);
    LOGD("Claim path is : %s",Claim_Path);
    FILE* secret_key = fopen(SK_Path,"rb");
    TFheGateBootstrappingSecretKeySet* keyset = new_tfheGateBootstrappingSecretKeySet_fromFile(secret_key);

    LweSample* answer = new_gate_bootstrapping_ciphertext_array(8,keyset->params);
    FILE * claim_data = fopen(Claim_Path,"rb");
    for(int i=0; i<8; i++)
        import_gate_bootstrapping_ciphertext_fromFile(claim_data, &answer[i], keyset->params);
    fclose(claim_data);

    int16_t int_answer = 0;
    int ai[8];
    for (int i=0; i<8; i++) {
        ai[i] = bootsSymDecrypt(&answer[i], keyset)>0;
        int_answer |= (ai[i]<<i);
    }
    LOGD("answer is : %d",int_answer);
    return int_answer;
}