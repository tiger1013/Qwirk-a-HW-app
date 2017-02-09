package com.avocado.qwirk;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class PlaceHolderFragment extends Fragment {
    String text;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.place_holder_fragment, container, false);

        TextView text = (TextView) view.findViewById(R.id.text);

        if (getArguments().get("text") != null)
            text.setText(getArguments().get("text").toString());
        return view;
    }
}
