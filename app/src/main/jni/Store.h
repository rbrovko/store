//
// Created by Brovko Roman on 24.08.17.
//

#ifndef STORE_STORE_H
#define STORE_STORE_H

#include <cstdint>

#define STORE_MAX_CAPACITY 16

typedef enum {

} StoreType;

typedef union {

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

#endif //STORE_STORE_H
