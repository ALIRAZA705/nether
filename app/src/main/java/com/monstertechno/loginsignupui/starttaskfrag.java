package com.monstertechno.loginsignupui;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**

 */
public class starttaskfrag extends Fragment {

 //  public Button starttravel,starttask;
 TextView textView,tasktext ;

    Button start, pause, reset, lap ;

    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;

    Handler handler;

    int Seconds, Minutes, MilliSeconds ;

    ListView listView ;

    String[] ListElements = new String[] {  };

    List<String> ListElementsArrayList ;

    ArrayAdapter<String> adapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_starttaskfrag, container, false);
        textView=  view.findViewById(R.id.textView);
        tasktext=  view.findViewById(R.id.tasktext);
        start = view.findViewById(R.id.button);
        pause = view.findViewById(R.id.button2);
        reset =  view.findViewById(R.id.button3);
        lap =  view.findViewById(R.id.button4) ;
        listView =  view.findViewById(R.id.listview1);
        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));
//
        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                ListElementsArrayList);

        listView.setAdapter(adapter);
        tasktext.setText("You are travelling");
        handler = new Handler() ;
        Bundle b2 = getArguments();
        if(b2!=null) {

            String name1 = b2.getString("name");
            Log.v("aliraza" , "value is" + name1);
            if (name1.compareTo("starttime") == 0)
            {
                tasktext.setText("You are working");
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        StartTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);

                        reset.setEnabled(false);


                    }
                });
            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    TimeBuff += MillisecondTime;

                    handler.removeCallbacks(runnable);

                    reset.setEnabled(true);

                }
            });
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MillisecondTime = 0L;
                    StartTime = 0L;
                    TimeBuff = 0L;
                    UpdateTime = 0L;
                    Seconds = 0;
                    Minutes = 0;
                    MilliSeconds = 0;

                    textView.setText("00:00:00");

                    ListElementsArrayList.clear();

//                    adapter.notifyDataSetChanged();
                }
            });
                lap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ListElementsArrayList.add(textView.getText().toString());

                        adapter.notifyDataSetChanged();

                    }
                });
//            textView.setText(name);

            }
           else if (name1.compareTo("starttravel") == 0)
            {
                tasktext.setText("You are travelling");
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        StartTime = SystemClock.uptimeMillis();
                        handler.postDelayed(runnable, 0);

                        reset.setEnabled(false);


                    }
                });
                pause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        TimeBuff += MillisecondTime;

                        handler.removeCallbacks(runnable);

                        reset.setEnabled(true);

                    }
                });
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        MillisecondTime = 0L;
                        StartTime = 0L;
                        TimeBuff = 0L;
                        UpdateTime = 0L;
                        Seconds = 0;
                        Minutes = 0;
                        MilliSeconds = 0;

                        textView.setText("00:00:00");

                        ListElementsArrayList.clear();

//                    adapter.notifyDataSetChanged();
                    }
                });
                lap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        ListElementsArrayList.add(textView.getText().toString());

                        adapter.notifyDataSetChanged();

                    }
                });
//            textView.setText(name);

            }
            else{
                Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();}

        }

//        starttravel=view.findViewById(R.id.start_travel);
//        starttask=view.findViewById(R.id.start_task);
//        //////////////////////////////
//        //start travel/////////////
//        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//
//        builder.setMessage("You are Travelling !");
//        builder.setTitle("Status in process");
//
//        //Setting message manually and performing action on button click
//        builder.setMessage("You are Travelling !");
//    //    CWinApp::SetDialogBkColor(COLORREF clrCtlBk, COLORREF clrCtlText)
//      //  builder.SetDialogBkColor(RGB(255, 0, 0), RGB(0, 255, 0));
//        //This will not allow to close dialogbox until user selects an option
//        builder.setCancelable(false);
//        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                Toast.makeText(getActivity(), "positive button", Toast.LENGTH_SHORT).show();
//                //builder.finish();
//            }
//        });
//        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                //  Action for 'NO' Button
//                Toast.makeText(getActivity(), "negative button", Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });

      //  MainActivity.starttravel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog alert = builder.create();
//                alert.show();
//
//            }
//        });
////
////////////////////////////////
//        //start task/////////////
//        //Creating dialog box
//
//
//
//        final AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
//
//
//        builder1.setMessage("You are Travelling !");
//        builder1.setTitle("Status in process");
//
//        //Setting message manually and performing action on button click
//        builder1.setMessage("Your are Working!");
//        //    CWinApp::SetDialogBkColor(COLORREF clrCtlBk, COLORREF clrCtlText)
//        //  builder.SetDialogBkColor(RGB(255, 0, 0), RGB(0, 255, 0));
//        //This will not allow to close dialogbox until user selects an option
//        builder1.setCancelable(false);
//        builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                Toast.makeText(getActivity(), "positive button", Toast.LENGTH_SHORT).show();
//                //builder.finish();
//            }
//        });
//        builder1.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                //  Action for 'NO' Button
//                Toast.makeText(getActivity(), "negative button", Toast.LENGTH_SHORT).show();
//                dialog.cancel();
//            }
//        });
//        starttask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog alert = builder1.create();
//
//                alert.show();
//                starttask.setText("End task");
//
//            }
//        });
        //Setting the title manually
        //alert.setTitle("AlertDialogExample");

        return view;
    }
    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
}
