//
// Created by Brovko Roman on 24.08.17.
//

#ifndef STORE_STORE_H
#define STORE_STORE_H

#include <cstdint>
#include "jni.h"

/*
 * Constants
 */
#define STORE_MAX_CAPACITY 16

/*
 * Types handled by the store
 */
typedef enum {
    StoreType_String,
    StoreType_Integer,
    StoreType_Boolean,
    StoreType_Byte,
    StoreType_Char,
    StoreType_Double,
    StoreType_Float,
    StoreType_Long,
    StoreType_Short,
    StoreType_Color,
    StoreType_IntegerArray,
    StoreType_StringArray,
    StoreType_ColorArray,
    StoreType_BooleanArray,
    StoreType_ByteArray,
    StoreType_CharArray,
    StoreType_DoubleArray,
    StoreType_FloatArray,
    StoreType_LongArray,
    StoreType_ShortArray
} StoreType;

/*
 * Store data structures
 */
typedef union {
    char* mString;
    int32_t mInteger;
    uint8_t mBoolean;
    int8_t mByte;
    uint16_t mChar;
    double mDouble;
    float mFloat;
    int64_t mLong;
    int16_t mShort;
    jobject mColor;
    int32_t* mIntegerArray;
    char** mStringArray;
    jobject* mColorArray;
    uint8_t* mBooleanArray;
    int8_t* mByteArray;
    uint16_t* mCharArray;
    double* mDoubleArray;
    float* mFloatArray;
    int64_t* mLongArray;
    int16_t* mShortArray;
} StoreValue;

typedef struct {
    char *mKey;
    StoreType mType;
    StoreValue mValue;
    int32_t mLength;  // Used only for arrays
} StoreEntry;

typedef struct {
    StoreEntry mEntries[STORE_MAX_CAPACITY];
    int32_t mLength;
} Store;

/*
 * Store helper methods
 */
bool isEntryValid(JNIEnv *pEnv, StoreEntry *pEntry, StoreType pType);
StoreEntry* allocateEntry(JNIEnv *pEnv, Store *pStore, jstring pKey);
StoreEntry* findEntry(JNIEnv *pEnv, Store *pStore, jstring pKey);
void releaseEntryValue(JNIEnv *pEnv, StoreEntry *pEntry);

/*
 * Methods to raise Java exceptions
 */
void throwInvalidTypeException(JNIEnv *pEnv);
void throwNotExistingKeyException(JNIEnv *pEnv);
void throwStoreFullException(JNIEnv *pEnv);

#endif //STORE_STORE_H
