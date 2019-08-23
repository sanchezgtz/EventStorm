package com.tahsan.eventstorm.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tahsan.eventstorm.R;


public class InstructorFragment extends Fragment {

    private static final String TAG = InstructorFragment.class.getSimpleName();

    private final String SAVED_BUNDLE_TAG = "saved_bundle";

    public static InstructorFragment newInstance() {
        InstructorFragment fragment = new InstructorFragment();
        return fragment;
    }

    public InstructorFragment() {
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: ");
        //outState.putBoolean(SAVED_BUNDLE_TAG, isChecked);
        super.onViewStateRestored(outState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_instructor, container, false);
        return view;
    }
}
