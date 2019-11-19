package com.example.johnproject2;

import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;


public class BroadcastLocations extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_location);

        EditText coordinate = findViewById(R.id.coordinate);
        EditText nameedt = findViewById(R.id.name_of_location);

        String coorcinates = coordinate.getText().toString();
        String name = nameedt.getText().toString();

        if(!TextUtils.isEmpty(coorcinates) || !TextUtils.isEmpty(name)) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", name);
            map.put("latlog", coorcinates);

            FirebaseFirestore.getInstance().collection("Generals").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(BroadcastLocations.this, "Address has been sent successfully", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        Toast.makeText(BroadcastLocations.this, "Please try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Toast.makeText(this, "Please make sure all the fields are filled", Toast.LENGTH_SHORT).show();
        }





        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
