package com.example.johnproject2;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SendLocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_location);
        String latLog = getIntent().getStringExtra("latLog");

        final EditText we = findViewById(R.id.name);
        final String email = we.getText().toString();
     final String name =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("user", "no");

        final Map<String, Object> value = new HashMap<>();
        value.put("latlog", latLog);
        value.put("name", name);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(email)){

                    FirebaseFirestore.getInstance().collection("Locations").document(email).collection("Value").add(value).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(SendLocationActivity.this, "Location has been sent successfully", Toast.LENGTH_SHORT).show();

                                finish();

                            }else {
                                Toast.makeText(SendLocationActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
                                we.setEnabled(true);
                            }

                        }
                    });

                }else {
                    Toast.makeText(SendLocationActivity.this, "Please make sure you enter your email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
