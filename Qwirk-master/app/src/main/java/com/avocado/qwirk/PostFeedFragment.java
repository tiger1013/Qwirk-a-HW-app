package com.avocado.qwirk;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;



public class PostFeedFragment extends Fragment {
    private ListView mListView;
    PostAdapter adapter;
    App app;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.posts_fragment, container, false);

        // Setting up listview for main feed with news posts
        mListView = (ListView) view.findViewById(R.id.main_list);
        app = (App) getActivity().getApplication();
        adapter = new PostAdapter(getActivity(), app.getPosts());
        Log.v("POSTFEEDFRAG", "adapter: " + (mListView == null));
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Post post = (Post) mListView.getItemAtPosition(position);
                Intent intent = new Intent(getActivity(), PostExpandedViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("username", post.getUserName());
                bundle.putString("text", post.getText());
                bundle.putInt("pic", post.picture);
                bundle.putInt("Id", post.getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddAssignmentActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        Log.v("POSTFEEDFRAGMENT", "OnResume has been called");
    }
}
