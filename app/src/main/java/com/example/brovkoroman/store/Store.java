package com.example.brovkoroman.store;

/**
 * Created by brovkoroman on 24.08.17.
 */

/**
 * Storage with native
 */
public class Store {
    static {
        System.loadLibrary("com_example_brovkoroman_store_Store");
    }

    /**
     * Count records in Store
     * @return count records in store
     */
    public native int getCount();

    public native String getString(String pKey);
    public native void setString(String pKey, String pString);
}
