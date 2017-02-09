package com.avocado.qwirk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView image = (ImageView) findViewById(R.id.user_image);
        image.setImageResource(R.drawable.shirwa);

        TextView tv = (TextView) findViewById(R.id.user);
        String steps="User Since: ";
        String title="Jan 2016";

        SpannableString ss = new SpannableString(steps);
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, ss.length(), 0);
        tv.append(ss);
        tv.append(title);

        TextView tv1 = (TextView) findViewById(R.id.user2);
        String steps1="Number of assignments submitted: ";
        String title1="4";

        SpannableString ss1 = new SpannableString(steps1);
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        tv1.append(ss1);
        tv1.append(title1);

        TextView tv2 = (TextView) findViewById(R.id.user3);
        String steps2="Number of completed assignments: ";
        String title2="3";

        SpannableString ss2 = new SpannableString(steps2);
        ss2.setSpan(new StyleSpan(Typeface.BOLD), 0, ss2.length(), 0);
        tv2.append(ss2);
        tv2.append(title2);

        TextView tv3 = (TextView) findViewById(R.id.user4);
        String steps3="Last login: ";
        String title3="0 days ago";

        SpannableString ss3 = new SpannableString(steps3);
        ss3.setSpan(new StyleSpan(Typeface.BOLD), 0, ss3.length(), 0);
        tv3.append(ss3);
        tv3.append(title3);

        TextView tv4 = (TextView) findViewById(R.id.user5);
        String steps4="Profile views: ";
        String title4="5,125";

        SpannableString ss4 = new SpannableString(steps4);
        ss4.setSpan(new StyleSpan(Typeface.BOLD), 0, ss4.length(), 0);
        tv4.append(ss4);
        tv4.append(title4);

        TextView tv5 = (TextView) findViewById(R.id.user6);
        String steps5="Friends: ";
        String title5="1,489";

        SpannableString ss5 = new SpannableString(steps5);
        ss5.setSpan(new StyleSpan(Typeface.BOLD), 0, ss5.length(), 0);
        tv5.append(ss5);
        tv5.append(title5);
    }
    public void newPage(View v) {
        Intent intent;
        switch(v.getId()) {
            //message user
            case R.id.imageButton2:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Send Message");
                alert.setMessage("To: Shirwa Mohamed");

                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Context context = getApplicationContext();
                        CharSequence text = "Message Delivered!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                });

                alert.show();
                break;
            //open calendar
            case R.id.imageButton3:
                intent = new Intent(Profile.this, AssignmentAdapter.class);
                startActivity(intent);
                break;
            //add friend
            case R.id.imageButton4:
                intent = new Intent(Profile.this, Profile.class);
                Toast.makeText(getApplicationContext(), "Friend added!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;
            //open misc
            case R.id.imageButton5:
                intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}






