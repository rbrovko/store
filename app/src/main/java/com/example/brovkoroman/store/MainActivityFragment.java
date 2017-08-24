package com.example.brovkoroman.store;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Store mStore = new Store();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        updateTitle();
        return rootView;
    }

    private void updateTitle() {
        int numEntries = mStore.getCount();

        getActivity().setTitle(String.format("Store (%1$s)", numEntries));
    }
}
