package com.example.brovkoroman.store;

/**
 * Created by brovkoroman on 28.08.17.
 */

public interface StoreListener {
    void onSuccess(int pValue);
    void onSuccess(String pValue);
    void onSuccess(Color pValue);
}
