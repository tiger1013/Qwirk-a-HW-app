package com.avocado.qwirk;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class PostAdapter extends ArrayAdapter<Post> {

    public PostAdapter(Context context, ArrayList<Post> posts) {
        super(context, 0, posts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_post, parent, false);
        }
        Post post = (Post) super.getItem(position);
        if (post != null) {
            TextView userName = (TextView) convertView.findViewById(R.id.username);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            TextView timeStamp = (TextView) convertView.findViewById(R.id.time_stamp);

            ImageView profilePic = (ImageView) convertView.findViewById(R.id.user_image);
            userName.setText(Html.fromHtml(post.getUserName()));
            if (post.picture != -1) {
                profilePic.setImageResource(post.picture);
            }
            textView.setText(post.getText());
            timeStamp.setText(post.timeStamp + "h");
        }
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public int getColor(int tint) {
        switch (tint) {
            case 1:
                return R.color.colorPink;
            case 2:
                return R.color.colorAccent;
            case 3:
                return R.color.colorYellow;
            default:
                return -1;
        }
    }
}
