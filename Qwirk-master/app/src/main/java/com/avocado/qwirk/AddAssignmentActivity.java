package com.avocado.qwirk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



public class AddAssignmentActivity extends AppCompatActivity {
    private Spinner spinnertime, spinnerdays;
    App app;
    private AutoCompleteTextView actv, autoCompleteTitle;
    String[] Class = {"CSCI 5115", "CSCI 5551", "CSCI 4061", "CSCI 5521"};
    String[] Title = {" User interface design", "Introduction To Intelligent Robotic Systems", "Introduction to operating system", "Introduction to Machine learning"};
    EditText title;
    EditText classInput;
    EditText descriptionInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add assignment");
        setContentView(R.layout.activity_add_assignment);
        actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        Button later = (Button) findViewById(R.id.btn_later);
        Button now = (Button) findViewById(R.id.btn_now);
        title = (EditText) findViewById(R.id.etxt_title);
        classInput = (EditText) findViewById(R.id.etxtclassname);
        descriptionInput = (EditText) findViewById(R.id.etxt_post);
        autoCompleteTitle = (AutoCompleteTextView) findViewById(R.id.autoCompleteTitle);
        app = (App) getApplication();
        spinnertime = (Spinner) findViewById(R.id.spinnertime);
        spinnerdays = (Spinner) findViewById(R.id.spinnerdays);
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAssignment();
            }
        });


        //When user click on button now.
        now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String classname = actv.getText().toString();
                String title = autoCompleteTitle.getText().toString();
                String time = spinnertime.getSelectedItem().toString();
                String days = spinnerdays.getSelectedItem().toString();

//                System.out.println("Class Name:- " + classname);
//                System.out.println("Title:- " + title);
//                System.out.println("Text:- " + posttxt);
//                System.out.println("Time:- " + time);
//                System.out.println("Days:- " + days);


//                if (classname.equals("") || title.equals("") || posttxt.equals("")) {
//                    Toast.makeText(getApplicationContext(), "Please fill All The Field", Toast.LENGTH_LONG).show();
//                } else {
//                    Assignment assignment = new Assignment(classname, posttxt, title, 2, 1);
//                    app.assignments.add(assignment);
//
//                    Toast.makeText(getApplicationContext(), "Record Inserted successfully.", Toast.LENGTH_LONG).show();
//                }

            }
        });

        //When user click on button Later.
        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeNow();
            }
        });

        //set adapter into autocomplete textview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Class);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        actv.setTextColor(Color.BLACK);

        //set adapter into autocomplete textview
        ArrayAdapter<String> adapterTitle = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, Title);
        autoCompleteTitle.setThreshold(1);//will start working from first character
        autoCompleteTitle.setAdapter(adapterTitle);//setting the adapter data into the AutoCompleteTextView
        autoCompleteTitle.setTextColor(Color.BLACK);
    }

    public void createAssignment() {
        String postTitle = "<b>Shirwa Mohamed</b> working on " + title.getText() + autoCompleteTitle.getText().toString();
        Log.v("ADDASSIGNMENTACTIVITY", "adding an assignment! title: " + postTitle);
        Post postObj = new Post(postTitle, descriptionInput.getText().toString(), R.drawable.shirwa, 0, app.getNextId());
        app.addPost(postObj, 0);
        Assignment assignment = new Assignment(app.getNextId(), actv.getText().toString(), descriptionInput.getText().toString(), autoCompleteTitle.getText().toString(), 5, 0);
        app.addAssignmentsList(assignment);
    }

    public void timeNow() {
        createAssignment();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //save into database
        //Toast.makeText(getApplicationContext(), "Working on assignment now", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void timeLater() {
        createAssignment();
        Intent intent = new Intent(getApplicationContext(), Assignment.class);
        //save into database
        Toast.makeText(getApplicationContext(), "Allocate time for later", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}

