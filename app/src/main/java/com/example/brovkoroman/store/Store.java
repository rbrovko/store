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

    public native int getInteger(String pKey);
    public native void setInteger(String pKey, int pInt);

    public native boolean getBoolean(String pKey);
    public native void setBoolean(String pKey, boolean pBoolean);

    public native byte getByte(String pKey);
    public native void setByte(String pKey, byte pByte);

    public native char getChar(String pKey);
    public native void setChar(String pKey, char pChar);

    public native double getDouble(String pKey);
    public native void setDouble(String pKey, double pDouble);

    public native float getFloat(String pKey);
    public native void setFloat(String pKey, float pFloat);

    public native long getLong(String pKey);
    public native void setLong(String pKey, long pLong);

    public native short getShort(String pKey);
    public native void setShort(String pKey, short pShort);
}
