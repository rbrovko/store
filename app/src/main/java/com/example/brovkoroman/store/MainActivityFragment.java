package com.example.brovkoroman.store;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Nullable;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Store mStore = new Store();
    private EditText mUIKeyEdit, mUIValueEdit;
    private Spinner mUITypeSpinner;
    private Button mUIGetButton, mUISetButton;
    private Pattern mKeyPattern;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        updateTitle();

        // Initializes text components
        mKeyPattern = Pattern.compile("\\p{Alnum}+");
        mUIKeyEdit = (EditText) rootView.findViewById(R.id.uiKeyEdit);
        mUIValueEdit = (EditText) rootView.findViewById(R.id.uiValueEdit);

        // Initializes type selector
        ArrayAdapter<StoreType> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, StoreType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mUITypeSpinner = (Spinner) rootView.findViewById(R.id.uiTypeSpinner);
        mUITypeSpinner.setAdapter(adapter);

        // Initializes buttons
        mUIGetButton = (Button) rootView.findViewById(R.id.uiGetValueButton);
        mUIGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGetValue();
            }
        });

        mUISetButton = (Button) rootView.findViewById(R.id.uiSetValueButton);
        mUISetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetValue();
            }
        });

        return rootView;
    }

    private void updateTitle() {
        int numEntries = mStore.getCount();

        getActivity().setTitle(String.format("Store (%1$s)", numEntries));
    }

    private void onGetValue() {
        // Retrieves key and type entered by the user
        String key = mUIKeyEdit.getText().toString();
        StoreType type = (StoreType) mUITypeSpinner.getSelectedItem();

        // Checks key is correct
        if (!mKeyPattern.matcher(key).matches()) {
            displayMessage("Incorrect key.");
            return;
        }

        // Retrieves value from the store and displays it
        switch (type) {
            case String:
                mUIValueEdit.setText(mStore.getString(key));
                break;

            case Integer:
                mUIValueEdit.setText(Integer.toString(mStore.getInteger(key)));
                break;

            case Boolean:
                mUIValueEdit.setText(Boolean.toString(mStore.getBoolean(key)));
                break;

            case Byte:
                mUIValueEdit.setText(Byte.toString(mStore.getByte(key)));
                break;

            case Char:
                mUIValueEdit.setText(Character.toString(mStore.getChar(key)));
                break;

            case Double:
                mUIValueEdit.setText(Double.toString(mStore.getDouble(key)));
                break;

            case Float:
                mUIValueEdit.setText(Float.toString(mStore.getFloat(key)));
                break;

            case Long:
                mUIValueEdit.setText(Long.toString(mStore.getLong(key)));
                break;

            case Short:
                mUIValueEdit.setText(Short.toString(mStore.getShort(key)));
                break;

            case Color:
                mUIValueEdit.setText(mStore.getColor(key).toString());
                break;

            case IntegerArray:
                mUIValueEdit.setText(Ints.join(";", mStore.getIntegerArray(key)));
                break;

            case StringArray:
                mUIValueEdit.setText(Joiner.on(";").join(mStore.getStringArray(key)));
                break;

            case ColorArray:
                mUIValueEdit.setText(Joiner.on(";").join(mStore.getColorArrya(key)));
                break;
        }
    }

    private void onSetValue() {
        // Retrieves key and type entered by the user
        String key = mUIKeyEdit.getText().toString();
        String value = mUIValueEdit.getText().toString();
        StoreType type = (StoreType) mUITypeSpinner.getSelectedItem();

        // Checks key is correct
        if (!mKeyPattern.matcher(key).matches()) {
            displayMessage("Incorrect key.");
            return;
        }

        // Parses user entered value and saves it in the store
        try {
            switch (type) {
                case String:
                    mStore.setString(key, value);
                    break;

                case Integer:
                    mStore.setInteger(key, Integer.parseInt(value));
                    break;

                case Boolean:
                    mStore.setBoolean(key, Boolean.parseBoolean(value));
                    break;

                case Byte:
                    mStore.setByte(key, Byte.parseByte(value));
                    break;

                case Char:
                    if (value.length() == 1) {
                        mStore.setChar(key, value.charAt(0));
                    } else {
                        throw  new IllegalArgumentException();
                    }
                    break;

                case Double:
                    mStore.setDouble(key, Double.parseDouble(value));
                    break;

                case Float:
                    mStore.setFloat(key, Float.parseFloat(value));
                    break;

                case Long:
                    mStore.setLong(key, Long.parseLong(value));
                    break;

                case Short:
                    mStore.setShort(key, Short.parseShort(value));
                    break;

                case Color:
                    mStore.setColor(key, new Color(value));
                    break;

                case IntegerArray:
                    mStore.setIntegerArray(key, Ints.toArray(stringToList(new Function<String, Integer>() {
                        @Nullable
                        @Override
                        public Integer apply(@Nullable String input) {
                            return Integer.parseInt(input);
                        }
                    }, value)));
                    break;

                case StringArray:
                    String[] stringArray = value.split(";");
                    mStore.setStringArray(key, stringArray);
                    break;

                case ColorArray:
                    List<Color> idList = stringToList(new Function<String, Color>() {
                        @Nullable
                        @Override
                        public Color apply(@Nullable String input) {
                            return new Color(input);
                        }
                    }, value);
                    mStore.setColorArray(key, idList.toArray(new Color[idList.size()]));
                    break;
            }
        } catch (Exception eException) {
            displayMessage("Incorrect value");
        }
        updateTitle();
    }

    /**
     * Helper method to display a message
     * @param pMesage Message
     */
    private void displayMessage(String pMesage) {
        Toast.makeText(getActivity(), pMesage, Toast.LENGTH_LONG).show();
    }

    /**
     * Helper method to convert a string to a list of values
     * @param pConversion Function
     * @param pValue String values
     * @param <TType> Type
     * @return List of values
     */
    @NonNull
    private <TType>List<TType> stringToList(Function<String, TType> pConversion, String pValue) {
        String[] splitArray = pValue.split(";");
        List<String> splitList = Arrays.asList(splitArray);

        return Lists.transform(splitList, pConversion);
    }
}
