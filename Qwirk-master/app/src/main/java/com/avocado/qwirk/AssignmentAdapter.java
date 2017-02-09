package com.avocado.qwirk;

import android.widget.ArrayAdapter;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class AssignmentAdapter extends ArrayAdapter<Assignment> {

    public AssignmentAdapter(Context context, ArrayList<Assignment> assignments) {
        super(context, 0, assignments);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_assignment, parent, false);
        }
        Assignment assn = (Assignment) super.getItem(position);
        if (assn != null) {
            TextView clssName = (TextView) convertView.findViewById(R.id.clss);
            TextView textView = (TextView) convertView.findViewById(R.id.text);
            TextView timeStamp = (TextView) convertView.findViewById(R.id.time_stamp);

            //ImageView profilePic = (ImageView) convertView.findViewById(R.id.user_image);
            clssName.setText(Html.fromHtml(assn.getClss()));
 /*           if (assn.picture != -1) {
                profilePic.setImageResource(assn.picture);
            }*/
            textView.setText(assn.getText());
            timeStamp.setText(assn.days + " days");

            int tint = getColor(assn.getTint());
        }
        return convertView;
    }

    public int getColor(int tint) {
        switch (tint) {
            case 1:
                return Color.rgb(0, 220, 0);
            case 2:
                return R.color.colorYellow;
            case 3:
                return Color.rgb(220, 0, 0);
            default:
                return -1;
        }
    }
}
