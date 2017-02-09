package com.avocado.qwirk;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.widget.Button;

import java.util.ArrayList;


public class CalendarFragment extends Fragment {
    App app;
    ViewPagerAdapter adapter;
    Button listView;

    public CalendarFragment() {
    }

    @SuppressLint("ValidFragment")
    public CalendarFragment(ViewPagerAdapter adapter) {
        this.adapter = adapter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        app = (App) getActivity().getApplication();
        View view = inflater.inflate(R.layout.calendar_page, container, false);
        listView = (Button) view.findViewById(R.id.list);
        /*listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToListView();
            }
        });*/
        return view;
    }

    /*public void switchToListView() {
        AssignmentFeedFragment frag = new AssignmentFeedFragment();
        FragmentTransaction trans = getFragmentManager()
                .beginTransaction();
        trans.replace(R.id.fragment_calendar, frag);
        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        trans.addToBackStack(null);
        trans.commit();
    }*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    public void init() {
        TableLayout tbl = (TableLayout) getView().findViewById(R.id.tableLayout2);

        //dummy data
        Block testBlock1 = new Block(2, 34, 40, 0x55555500);
        Block testBlock2 = new Block(4, 20, 25, 0xffffff00);
        Block[] blocks = {testBlock1, testBlock2};

        Assignment assign1 = new Assignment(0, "Robotics (CSCI 5551)", "desc.", "HW1", 4, 0xff6DC5E3);
        assign1.addBlock(testBlock1);
        Assignment assign2 = new Assignment(1, "UI (CSCI 5115)", "desc.", "Design Log", 3, 0xffE38B6D);
        assign2.addBlock(testBlock2);

        app.assignments = new ArrayList<Assignment>();
        app.assignments.add(assign1);
        app.assignments.add(assign2);


        //dummy data

        int hour = 1;
        for (int i = 1; i < 48; i++) {

            TableRow row = new TableRow(getActivity());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            TextView tv1 = new TextView(getActivity());
            tv1.setGravity(Gravity.CENTER);
            tv1.setWidth(10);
            //ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) tv1.getLayoutParams();
            //mlp.topMargin = -5;
            //tv.setId()?\


            if (i % 2 == 1) {
                tv1.setText("   ");
            } else {
                int time = hour % 12;
                if (time == 0) {
                    time = 12;
                }
                if (i < 24) {
                    tv1.setText(String.valueOf(time) + "a");
                } else {
                    tv1.setText(String.valueOf(time) + "p");
                }
                hour++;
            }

            row.addView(tv1);
            for (int j = 1; j < 8; j++) {
                TextView tv2 = new TextView(getActivity());
                tv2.setId((i * 10) + j);
                tv2.setLayoutParams(new TableRow.LayoutParams(j));
                tv2.setBackgroundResource(R.drawable.cell_shape);
                tv2.setPadding(2, 2, 2, 2);

                row.addView(tv2);
            }


            tbl.addView(row, i);
        }
        final ScrollView sv = (ScrollView) getView().findViewById(R.id.scrollView1);
        sv.scrollTo(0, 1000);

        populate();
    }

    private void populate() {
        final ArrayList<Assignment> a = app.assignments;
        if (a.size() != 0) {
            activateAssignment(app.assignments.get(0));
            for (int i = 0; i < a.size(); i++) {
                final Assignment currentAssign = a.get(i);
                int jsize = currentAssign.getNumBlocks();
                for (int j = 0; j < jsize; j++) {
                    Block b = currentAssign.getBlock(j);
                    int day = b.getDay();
                    int startTime = b.getStartTime();
                    int endTime = b.getEndTime();
                    int color = b.getColor();

                    for (int k = startTime; k <= endTime; k++) {
                        int index = (k * 10) + day;
                        TextView temp = (TextView) getView().findViewById(index);
                        temp.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
                        final int l = i;
                        temp.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View temp) {
                                //deleteBlock(b[l]);
                                activateAssignment(currentAssign);

                            }
                        });
                    }
                }
            }
        }
    }

    private void eraseBlock(Block b) {
        int day = b.getDay();
        int startTime = b.getStartTime();
        int endTime = b.getEndTime();
        int color = b.getColor();


        for (int j = startTime; j <= endTime; j++) {
            int index = (j * 10) + day;
            TextView temp = (TextView) getView().findViewById(index);
            temp.getBackground().clearColorFilter();
            temp.setOnClickListener(null);
        }
    }

    private void activateAssignment(final Assignment a) {

        RelativeLayout window = (RelativeLayout) getView().findViewById(R.id.window);
        window.getBackground().setColorFilter(a.getTint(), PorterDuff.Mode.SRC_IN);

        TextView clss = (TextView) getView().findViewById(R.id.title);
        clss.setText(a.getClss() + " - " + a.getTitle());

        //assign name or due date?
        TextView due = (TextView) getView().findViewById(R.id.due);
        due.setText("Due in " + a.getDays() + " days");

        Button button = (Button) getView().findViewById(R.id.setTime);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View temp) {
                allocateTime(a);
            }
        });

        Button button2 = (Button) getView().findViewById(R.id.complete);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View temp) {
                int jsize = a.getNumBlocks();
                for (int j = 0; j < jsize; j++) {
                    Block b = a.getBlock(j);
                    eraseBlock(b);
                }
                app.assignments.remove(a);
                if (app.assignments.size() != 0) {
                    activateAssignment(app.assignments.get(0));
                }
            }
        });

        //add event listeners

    }

    private void allocateTime(final Assignment a) {
        ArrayList<Integer> newCells = new ArrayList<>();

        Button button = (Button) getView().findViewById(R.id.setTime);
        button.setText("Done");
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View temp) {
                Button button = (Button) getView().findViewById(R.id.setTime);
                button.setText("Set Time");
                clearListeners();
                populate();
                activateAssignment(a);
            }
        });

        for (int i = 1; i < 48; i++) {
            for (int j = 1; j < 8; j++) {
                TextView temp = (TextView) getView().findViewById((i * 10) + j);
                temp.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View temp) {
                        int id = temp.getId();
                        int day = id%10;
                        int startTime = ((id - day)/10);
                        if(!a.hasBlock(day, startTime)) {
                            int color = a.getTint();
                            temp.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_IN);
                            Block newBlock = new Block(day, startTime, startTime, color);
                            a.addBlock(newBlock);
                        }
                        else{
                            temp.getBackground().clearColorFilter();
                            a.deleteBlock(day, startTime);
                        }
                    }
                });
            }
        }
    }

    private void clearListeners() {
        for (int i = 1; i < 48; i++) {
            for (int j = 1; j < 8; j++) {
                TextView temp = (TextView) getView().findViewById((i * 10) + j);
                temp.setOnClickListener(null);
            }
        }
    }
}
