package com.example.johnproject2.Registration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.johnproject2.R;
import com.example.johnproject2.maplocationpicker.LocationPickerActivity;
import com.google.firebase.auth.FirebaseAuth;

public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage_activity);

        DecideWhichToStart();
        findViewById(R.id.admin_only).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StartPageActivity.this);
                final EditText edittext = new EditText(getApplicationContext());
                alert.setMessage("Enter Your Password");
                alert.setTitle("Password");

                alert.setView(edittext);

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                      //  Editable YouEditTextValue = edittext.getText();
                        //OR
                        String YouEditTextValue = edittext.getText().toString();
                        if(YouEditTextValue.equals("123456")){
                            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                            PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("admin", "yes").apply();
                        }else {
                            dialog.cancel();
                        }
                    }
                });

               /* alert.setNegativeButton("No Option", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });*/

                alert.show();
            }
        });


        findViewById(R.id.driver_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("admin", "no").apply();

                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
            }
        });
    }



    private void DecideWhichToStart() {

       if(FirebaseAuth.getInstance().getCurrentUser() != null){

           startActivity(new Intent(getApplicationContext(), LocationPickerActivity.class));
       }
    }
}
