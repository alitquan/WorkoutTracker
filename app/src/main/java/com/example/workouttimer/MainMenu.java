package com.example.workouttimer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.workouttimer.databinding.FragmentFirstBinding;
import com.example.workouttimer.databinding.FragmentMainMenuBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */


public class MainMenu extends Fragment {

    private FragmentMainMenuBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Timer.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenu newInstance(String param1, String param2) {
        MainMenu fragment = new MainMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main_menu, container, false);

        binding = FragmentMainMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.menuButton1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Option 1");
                Log.d("Menu Button 1","Functional");
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_MainMenu_to_WorkoutList);
            }
        });


        binding.menuButton2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Option 2");
                Log.d("Menu Button 2","Functional");
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_MainMenuToEnd);
            }
        });

        binding.menuButton3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Option 3");
                Log.d("Menu Button 3","Functional");
                NavHostFragment.findNavController(MainMenu.this)
                        .navigate(R.id.action_MainMenuToTimer);
            }
        });
    }
}