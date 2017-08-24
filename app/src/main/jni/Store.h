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
    StoreType_String
} StoreType;

/*
 * Store data structures
 */
typedef union {
    char *mString;
} StoreValue;

typedef struct {
    char *mKey;
    StoreType mType;
    StoreValue mValue;
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

#endif //STORE_STORE_H
