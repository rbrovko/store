package com.example.brovkoroman.store;

/**
 * Created by brovkoroman on 24.08.17.
 */

import com.example.brovkoroman.exception.InvalidTypeException;
import com.example.brovkoroman.exception.NotExistingKeyException;

/**
 * Storage with native
 */
public class Store implements StoreListener {

    private StoreListener mListener;

    static {
        System.loadLibrary("com_example_brovkoroman_store_Store");
    }

    public Store(StoreListener pListener) {
        mListener = pListener;
    }

    /**
     * Count records in Store
     * @return count records in store
     */
    public native int getCount();

    public native String getString(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setString(String pKey, String pString);

    public native int getInteger(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setInteger(String pKey, int pInt);

    public native boolean getBoolean(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setBoolean(String pKey, boolean pBoolean);

    public native byte getByte(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setByte(String pKey, byte pByte);

    public native char getChar(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setChar(String pKey, char pChar);

    public native double getDouble(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setDouble(String pKey, double pDouble);

    public native float getFloat(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setFloat(String pKey, float pFloat);

    public native long getLong(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setLong(String pKey, long pLong);

    public native short getShort(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setShort(String pKey, short pShort);

    public native Color getColor(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setColor(String pKey, Color pColor);

    public native int[] getIntegerArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setIntegerArray(String pKey, int[] pIntArray);

    public native String[] getStringArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setStringArray(String pKey, String[] pStringArray);

    public native Color[] getColorArrya(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setColorArray(String pKey, Color[] pColorArray);

    public native boolean[] getBooleanArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setBooleanArray(String pKey, boolean[] pBooleanArray);

    public native byte[] getByteArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setByteArray(String pKey, byte[] pByteArray);

    public native char[] getCharArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setCharArray(String pKey, char[] pCharArray);

    public native double[] getDoubleArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setDoubleArray(String pKey, double[] pDoubleArray);

    public native float[] getFloatArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setFloatArray(String pKey, float[] pFloatArray);

    public native long[] getLongArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setLongArray(String pKey, long[] pLongArray);

    public native short[] getShortArray(String pKey) throws NotExistingKeyException, InvalidTypeException;
    public native void setShortArray(String pKey, short[] pShortArray);

    @Override
    public void onSuccess(int pValue) {
        mListener.onSuccess(pValue);
    }

    @Override
    public void onSuccess(String pValue) {
        mListener.onSuccess(pValue);
    }

    @Override
    public void onSuccess(Color pValue) {
        mListener.onSuccess(pValue);
    }
}
