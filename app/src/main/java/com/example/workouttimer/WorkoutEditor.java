package com.example.workouttimer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workouttimer.databinding.FragmentWorkoutEditorBinding;
import com.example.workouttimer.dbfiles.WorkoutDbHelper;

import java.util.Arrays;

public class WorkoutEditor extends Fragment {

    public static final String workoutKey = "chosenWorkout";

    private FragmentWorkoutEditorBinding binding;

    public static WorkoutEditor newInstance (String data) {
        WorkoutEditor newFragment = new WorkoutEditor();
        Bundle args = new Bundle();
        args.putString(workoutKey,data);
        newFragment.setArguments(args);
        return newFragment;
    }

    private String getArgs() {
         Bundle args = getArguments();

         if (args != null) {
             return args.getString("chosenWorkout");
         }
         return null;
    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentWorkoutEditorBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableLayout = (TableLayout) getView().findViewById(R.id.tableLayout1);
        WorkoutDbHelper dbhelper = new WorkoutDbHelper(getActivity());
        String [] workouts = dbhelper.returnAllExercises();
        Log.d("SecondFragment", "returning all");
        Log.d("SecondFragment", Arrays.toString(workouts));
        dbhelper.close();


        for (int i = 0; i< workouts.length; i++) {
            TableRow row = new TableRow(getActivity());
            TextView textView = new TextView(getActivity());


            String [] output = workouts[i].split(",");
            for (String n:output) {
                TextView column = new TextView(getActivity());
                column.setText(n);

                // making the border
                ShapeDrawable border = new ShapeDrawable(new RectShape());
                border.getPaint().setStyle(Paint.Style.STROKE);
                border.getPaint().setColor(Color.BLACK);
                border.getPaint().setStrokeWidth(5);
                column.setPadding(10,10,10,10);
                column.setBackground(border);


                row.addView(column);


            }
            tableLayout.addView(row);
            /*
            textView.setText(workouts[i]);
            row.addView(textView);
            tableLayout.addView(row);
             */
        }

        binding.addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getArguments();
                Log.d("WorkoutEditor-click","foo");
                Log.d("WorkoutEditor-click",Integer.toString(bundle.size()));
                Log.d("WorkoutEditor-click",bundle.getString(workoutKey));

                String content = binding.exerciseName.getText().toString();
                Log.d("WorkoutEditor-click",content);

//                if (bundle!=null) {
//                    String workout = bundle.getString(workoutKey);
//                    Log.d("WorkoutEditor-click", workout);
//                }
//                else {
//                    Log.d("WorkoutEditor-click", "Did not work");
//                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}