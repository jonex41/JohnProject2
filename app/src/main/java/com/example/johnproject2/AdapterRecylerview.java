package com.example.johnproject2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class AdapterRecylerview extends RecyclerView.Adapter<AdapterRecylerview.ViewHolder> {

    private Context context;
    private List<ModelClass> stringList;

    public AdapterRecylerview(Context context, List<ModelClass> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public AdapterRecylerview.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterRecylerview.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_model, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecylerview.ViewHolder holder, int position) {

       final ModelClass modelClass = stringList.get(position);

        holder.nameText.setText(modelClass.getName());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+Double.valueOf(modelClass.getLat())+","+Double.valueOf(modelClass.getLongi()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
                String uri = String.format(Locale.ENGLISH, "geo:%f,%f",  11.15049855,7.652441932);

            }
        });

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameText;
        private View view;

        public ViewHolder( View itemView) {
            super(itemView);
            view = itemView;
            nameText = itemView.findViewById(R.id.name);
        }
    }
}
