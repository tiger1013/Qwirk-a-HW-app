package com.avocado.qwirk;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;




public class AssignmentFeedFragment extends Fragment {
    private ListView mListView;
    AssignmentAdapter adapter;
    App app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.assignment_fragment, container, false);
        app = (App) getActivity().getApplication();
        // Setting up listview for main feed with news posts
        mListView = (ListView) view.findViewById(R.id.main_list);

        getActivity().findViewById(R.id.fab).setVisibility(View.VISIBLE);

        adapter = app.getAssignmentAdapter();
        Log.v("ASSNFEEDFRAG", "adapter: " + (mListView == null));
        mListView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
