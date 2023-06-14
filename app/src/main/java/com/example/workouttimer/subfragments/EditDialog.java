package com.example.workouttimer.subfragments;
import com.example.workouttimer.R;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class EditDialog extends DialogFragment{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add an Exercise");

// Set up the input
        final EditText input = new EditText(getActivity());
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(R.layout.form_add_exercise);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //m_Text = input.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        return builder.show();

//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Form")
//                .setMessage("Enter your values here")
//                .setPositiveButton("Submit", (dialog, which) -> {
//                    // Handle the form submission here
//                })
//                .setNegativeButton("Cancel", (dialog, which) -> {
//                    // Handle cancel button click here
//                });
//
//        return builder.create();
    }
}
