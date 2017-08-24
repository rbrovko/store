package com.example.brovkoroman.store;

/**
 * Created by brovkoroman on 24.08.17.
 */

/**
 * Storage with native
 */
public class Store {
    static {
        System.loadLibrary("com_packtpub_store_Store");
    }

    /**
     * Count records in Storage
     * @return count records in storage
     */
    public native int getCount();
}
