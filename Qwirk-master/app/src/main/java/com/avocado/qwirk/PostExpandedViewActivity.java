package com.avocado.qwirk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class PostExpandedViewActivity extends Activity {
    TextView userName;
    TextView text;
    ImageView profilePic;
    ListView commentList;
    ImageView submit;
    int id;
    ArrayList<Post> comments;
    App app;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_expanded_view);
        app = (App) getApplication();

        userName = (TextView) findViewById(R.id.username);
        text = (TextView) findViewById(R.id.text);
        profilePic = (ImageView) findViewById(R.id.user_image);
        submit = (ImageView) findViewById(R.id.submit);
        commentList = (ListView) findViewById(R.id.comments_list);

        Bundle b = getIntent().getExtras();
        userName.setText(Html.fromHtml(b.getString("username")));
        text.setText(b.getString("text"));
        profilePic.setImageResource(b.getInt("pic"));
        id = b.getInt("Id");
        comments = app.getComments(id);
        adapter = new PostAdapter(this, comments);
        commentList.setAdapter(adapter);
        final EditText text = (EditText) findViewById(R.id.add_comment);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text.getText().length() > 0)
                    createComment(text.getText().toString());
            }
        });

        text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                createComment(text.getText().toString());
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                text.setText("");
                return true;
            }
        });
    }

    public void log(String text) {
        Log.v("POSTEXPANDEDVIEW", text);
    }

    public void createComment(String text) {
        Post post1 = new Post("Shirwa Mohamed", text, R.drawable.shirwa, 0, app.getNextId());
        app.addComment(post1, id);
        adapter.notifyDataSetChanged();
    }
}
