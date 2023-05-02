package com.example.workouttimer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;


import com.example.workouttimer.databinding.FragmentWorkoutListBinding;
import com.example.workouttimer.dbfiles.WorkoutDbHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkoutList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkoutList extends Fragment {

    private FragmentWorkoutListBinding binding;
    private ArrayAdapter<String> adapter;

    public WorkoutList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorkoutList.
     */
    // TODO: Rename and change types and number of parameters
    public static WorkoutList newInstance(String param1, String param2) {
        WorkoutList fragment = new WorkoutList();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        WorkoutDbHelper dbhelper = new WorkoutDbHelper(getActivity());
        ArrayList<String> workouts = new ArrayList<String>();
        for (String s: dbhelper.returnAllWorkouts()) {
            workouts.add(s);
        }
        Log.d("WorkoutList", "Returned all workouts");
        dbhelper.close();


        // Inflate the layout for this fragment
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, workouts);

        binding = FragmentWorkoutListBinding.inflate(inflater, container, false);
        binding.workoutList.setAdapter(adapter);
        binding.workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                    String word = parent.getItemAtPosition(i).toString();
                    Log.d("WorkoutList-click",word);
                    initiateEditor(word);
            }
        });



        return binding.getRoot();

    }


    private void initiateEditor(String data) {
        WorkoutDbHelper dbhelper = new WorkoutDbHelper(getActivity());
        dbhelper.returnRelevantExercises(data);
        dbhelper.close();

        WorkoutEditor editor = WorkoutEditor.newInstance(data); // Pass the data as an argument
        FragmentManager manager = getParentFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.workoutlist_container,editor);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        binding.addWorkoutName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Log.d("WorkoutList-ovc","Cleared");
               String content = binding.workoutName.getText().toString();
               Log.d("WorkoutList-ovc",content);


                WorkoutDbHelper dbhelper = new WorkoutDbHelper(getActivity());
                dbhelper.addWorkout2(content);
                Log.d("WorkoutList-oc", "Added workout");


                adapter.add(content);
                adapter.notifyDataSetChanged();
                dbhelper.close();



            }
        });

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}