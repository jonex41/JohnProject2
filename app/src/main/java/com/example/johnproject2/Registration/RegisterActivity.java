package com.example.johnproject2.Registration;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;

import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.johnproject2.R;
import com.example.johnproject2.maplocationpicker.LocationPickerActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener {

    private EditText email, password1, password2, name;
    private Button submit, login;
    private TextView signIn;

    private String value, string_matric;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);


        email = findViewById(R.id.txt_email);
        password1 = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        signIn = findViewById(R.id.signin);

        signIn.setOnClickListener(this);
        submit = findViewById(R.id.create_account);
        submit.setOnClickListener(this);
        name = findViewById(R.id.name);
        value =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user", "no");



    }

    @Override
    public void onClick(View view) {

        int i = view.getId();

        switch (i){

            case R.id.create_account:
                String emailString = email.getText().toString().trim();
                String passwordString1 = password1.getText().toString().trim();
                String passwprdString2 = password2.getText().toString().trim();

                String nameString = name.getText().toString().trim();


                CreateAccount(emailString, passwordString1, passwprdString2, nameString);

                break;

            case R.id.signin:
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
                break;

        }

    }


    private void CreateAccount(final String emailString, String passwordString1, String passwprdString2, final String nameString) {

        if(TextUtils.isEmpty(emailString) || TextUtils.isEmpty(passwordString1) ||TextUtils.isEmpty(passwprdString2) ||TextUtils.isEmpty(nameString)){

            Toast.makeText(this, "Please make sure all the fields are filled", Toast.LENGTH_SHORT).show();
            return;
        }


        if(passwordString1.length() <6){
            Toast.makeText(this, "Please password should not be less than six words or alphabet", Toast.LENGTH_SHORT).show();

            return;
        }

       // PreferenceManagerds.saveString(getApplicationContext(), nameString, "name");

        if(!passwordString1.equals(passwprdString2)){

            Toast.makeText(this, "Please make sure all the password are same", Toast.LENGTH_SHORT).show();

            return;
        }


       // PreferenceManagerds.saveString(getApplicationContext(), nameString, Constants.name);

       final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        progressDialog.setCancelable(false);
        progressDialog.show();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailString, passwordString1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    Map<String, Object> maps = new HashMap<>();
                   maps.put("name", nameString);

                    maps.put("email", emailString);


                    FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(maps).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("name", nameString).apply();
                                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("email", emailString).apply();

                                progressDialog.cancel();
                                startActivity(new Intent(getApplicationContext(), LocationPickerActivity.class));
                                finish();
                            }else {
                                progressDialog.cancel();
                                Toast.makeText(RegisterActivity.this, "Please try aagain later", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }else {
                    progressDialog.cancel();

                    Toast.makeText(RegisterActivity.this,  task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
