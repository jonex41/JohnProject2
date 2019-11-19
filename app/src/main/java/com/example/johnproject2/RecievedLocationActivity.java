package com.example.johnproject2;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RecievedLocationActivity extends AppCompatActivity {

    private List<ModelClass> modelClasses = new ArrayList<>();
    private AdapterRecylerview adapterRecylerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recieved_location);

        RecyclerView recyclyview = findViewById(R.id.recyclerView);
        recyclyview.setHasFixedSize(true);
        recyclyview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
         adapterRecylerview = new AdapterRecylerview(getApplicationContext(), modelClasses);
        recyclyview.setAdapter(adapterRecylerview);

        FecthDatas();
    }

    private void FecthDatas() {
        FirebaseFirestore.getInstance().collection("Generals").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText(RecievedLocationActivity.this, "Unable to fecth data", Toast.LENGTH_SHORT).show();
                    return;
                }


                for(DocumentSnapshot dm : queryDocumentSnapshots){

                    String name = dm.getString("name");
                    String latlong = dm.getString("latlog");

                    String[] allVal = latlong.split(",");

                    String lat = allVal[0];
                    String longi = allVal[1];
                    modelClasses.add(new ModelClass(name, lat, longi));
                    adapterRecylerview.notifyDataSetChanged();

                }



            }
        });
        String email =  PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("email", "no");
        FirebaseFirestore.getInstance().collection("Locations").document(email).collection("Value").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Toast.makeText(RecievedLocationActivity.this, "Unable to fecth data", Toast.LENGTH_SHORT).show();
                    return;
                }


                for(DocumentSnapshot dm : queryDocumentSnapshots){

                    String name = dm.getString("name");
                    String latlong = dm.getString("latlog");

                    String[] allVal = latlong.split(",");

                    String lat = allVal[0];
                    String longi = allVal[1];
                    modelClasses.add(new ModelClass(name, lat, longi));
                    adapterRecylerview.notifyDataSetChanged();

                }



            }
        });
    }
}
