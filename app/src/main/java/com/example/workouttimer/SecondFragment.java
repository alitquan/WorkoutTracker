package com.example.workouttimer;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.workouttimer.databinding.FragmentSecondBinding;
import com.example.workouttimer.dbfiles.WorkoutDbHelper;

import java.util.Arrays;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableLayout = (TableLayout) getView().findViewById(R.id.tableLayout1);
        WorkoutDbHelper dbhelper = new WorkoutDbHelper(getActivity());
        String [] workouts = dbhelper.returnAll();
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


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}