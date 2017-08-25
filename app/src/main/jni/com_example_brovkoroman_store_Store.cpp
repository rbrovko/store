//
// Created by Brovko Roman on 24.08.17.
//

#include <cstdlib>

#include "com_example_brovkoroman_store_Store.h"
#include "Store.h"

/**
 * Contains the unique store instance in a static variable created
 * when library is loaded
 */
static Store gStore;

JNIEXPORT jint JNI_OnLoad(JavaVM *pVM, void *reserved) {
    // Store initialization
    gStore.mLength = 0;
    return JNI_VERSION_1_6;
}

JNIEXPORT jint JNICALL Java_com_example_brovkoroman_store_Store_getCount
        (JNIEnv *pEnv, jobject pObject) {
    return gStore.mLength;
}

JNIEXPORT jstring JNICALL Java_com_example_brovkoroman_store_Store_getString
        (JNIEnv *pEnv, jobject pThis, jstring pKey) {
    StoreEntry *entry = findEntry(pEnv, &gStore, pKey);

    if (isEntryValid(pEnv, entry, StoreType_String)) {
        // Converts a C string into a Java String
        return pEnv->NewStringUTF(entry->mValue.mString);
    } else {
        return NULL;
    }
}

JNIEXPORT void JNICALL Java_com_example_brovkoroman_store_Store_setString
        (JNIEnv *pEnv, jobject pThis, jstring pKey, jstring pString) {
    // Turns the Java string into a temporary C string
    StoreEntry *entry = allocateEntry(pEnv, &gStore, pKey);

    if (entry != NULL) {
        entry->mType = StoreType_String;

        /*
         * Copy the temporary C string into its dynamically allocated
         * final location. Then releases the temporary string
         */
        jsize  stringLenght = pEnv->GetStringUTFLength(pString);
        entry->mValue.mString = new char[stringLenght + 1];

        // Directly copies the Java String into our new C buffer
        pEnv->GetStringUTFRegion(pString, 0, stringLenght, entry->mValue.mString);

        // Append the null character for string termination
        entry->mValue.mString[stringLenght] = '\0';
    }
}

JNIEXPORT jint JNICALL Java_com_example_brovkoroman_store_Store_getInteger
        (JNIEnv *pEnv, jobject pThis, jstring pKey) {
    StoreEntry *entry = findEntry(pEnv, &gStore, pKey);

    if (isEntryValid(pEnv, entry, StoreType_Integer)) {
        return entry->mValue.mInteger;
    } else {
        return 0;
    }
}

JNIEXPORT void JNICALL Java_com_example_brovkoroman_store_Store_setInteger
        (JNIEnv *pEnv, jobject pThis, jstring pKey, jint pInteger) {
    StoreEntry *entry = allocateEntry(pEnv, &gStore, pKey);

    if (entry != NULL) {
        entry->mType = StoreType_Integer;
        entry->mValue.mInteger = pInteger;
    }
}
