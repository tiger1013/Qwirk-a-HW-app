package com.avocado.qwirk;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;



public class App extends Application {
    public ArrayList<Assignment> assignments;
    public ArrayList<Assignment> assignmentsList;
    ArrayList<Post> posts = new ArrayList<Post>();
    public final static String AUTHPREFS = "AUTHPREFS";
    AssignmentAdapter assignmentAdapter;

    HashMap<Integer, ArrayList<Post>> comments;
    int id = 0;

    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setDefaults();
    }

    public AssignmentAdapter getAssignmentAdapter() {
        return assignmentAdapter;
    }

    public void updateAdapter() {
        assignmentAdapter.notifyDataSetChanged();
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        comments.put(post.id, new ArrayList<Post>());
        Log.v("APP", "adding a post with: ! " + post.getText() + post.getUserName());
    }

    public void addPost(Post post, int index) {
        posts.add(index, post);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void addAssignmentsList(Assignment assignment) {
        Collections.sort(assignmentsList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment assignment, Assignment t1) {
                return assignment.getDays() - t1.getDays();
            }
        });
        assignmentsList.add(assignment);
        updateAdapter();
    }

    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public ArrayList<Assignment> getAssignmentsList() {
        Collections.sort(assignmentsList, new Comparator<Assignment>() {
            @Override
            public int compare(Assignment assignment, Assignment t1) {
                return assignment.getDays() - t1.getDays();
            }
        });
        return assignmentsList;
    }

    public void addComment(Post p, int id) {
        comments.get(id).add(p);
    }

    public int getNextId() {
        int temp = id;
        id++;
        return temp;
    }

    ArrayList<Post> getComments(int id) {
        if (comments.get(id) == null)
            comments.put(id, new ArrayList<Post>());
        return comments.get(id);
    }

    public int getSize() {
        return posts.size();
    }

    public void setDefaults() {
        assignments = new ArrayList<>();
        comments = new HashMap<>();
        assignmentsList = new ArrayList<>();
        assignmentAdapter = new AssignmentAdapter(this.getApplicationContext(), assignmentsList);
        setupList();
        String placeHolder1 = "I will be working in Keller 2-260 so if anyone wants to join, just lmk :)";
        String placeHolder2 = "I will be working in Keller 3-125. I have most of it done, but stuck on the last two";
        String placeHolder3 = "This will be a cake walk. I can do recursion in my sleep!";
        String placeHolder4 = "I wish I had an AI that would this for me";
        String placeHolder5 = "This will be a cake walk. I can do ";
        String source = "<b>Shirwa Mohamed</b> is working on OS Assignment 1";
        String source2 = "<b>Ben Hunder</b> is working on Algorithms Assignment 2";
        String source3 = "<b>Duy Nguyen</b> is working on Java Assignment 4";
        String source4 = "<b>Cooper Dombeck</b> is working on AI Assignment 3";
        String source5 = "<b>Yuxiang Wang</b> is working on UI Assignment 4";
        Post post = new Post(source, placeHolder1, R.drawable.shirwa, 1, getNextId());
        Post post1 = new Post(source2, placeHolder2, R.drawable.ben, 2, getNextId());
        Post post3 = new Post(source4, placeHolder4, R.drawable.gorb, 5, getNextId());
        Post post2 = new Post(source3, placeHolder3, R.drawable.default_profile, 6, getNextId());
        Post post4 = new Post(source5, placeHolder5, R.drawable.default_profile, 4, getNextId());
        addPost(post);
        addPost(post1);
        addPost(post3);
        addPost(post2);
        addPost(post4);
    }

    public void setupList() {

        // String clss, String text, String title, int days, int tint
        String placeHolder1 = "Robotics HW 5";
        String placeHolder2 = "UI presentation plan";
        String placeHolder4 = "UI final report paper";
        String source = "CSCI 5551";
        String source3 = "";
        String title1 = "<b>Class 1</b>";
        String title2 = "<b>Ben Hunder</b> is working on Algorithms Assignment 2";
        String title3 = "<b>Duy Nguyen</b> is working on Java Assignment 4";

        //lazy id assigning, not unique.
        Assignment post1 = new Assignment(41, source, placeHolder2, title2, 1, 3);
        Assignment post = new Assignment(40, source, placeHolder1, title1, 4, 1);


        assignmentsList.add(post1);
        assignmentsList.add(post);
    }
}

