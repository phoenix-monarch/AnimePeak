package com.example.animepeak.Adapters;

import static com.example.animepeak.Activity.Anime_Details.Title;
import static com.example.animepeak.Sources.Hanime.Hanime_details.HID;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animepeak.Activity.VideoPlayer;
import com.example.animepeak.R;

import org.json.JSONArray;

import org.json.JSONObject;

public class Ani_Details_Adapter extends RecyclerView.Adapter<Ani_Details_Adapter.ViewHolder> {
    JSONArray episodes;
    Activity activity;
    String name = null;
    String id = null;

    public Ani_Details_Adapter(JSONArray episodes, Activity activity) {
        this.episodes = episodes;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Ani_Details_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.episode_list, parent, false);
        Ani_Details_Adapter.ViewHolder viewHolder = new Ani_Details_Adapter.ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull Ani_Details_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        try {
            // Get the first element of the array

            SharedPreferences sharedpreferences = activity.getSharedPreferences("Settings", Context.MODE_PRIVATE);
            String Source = sharedpreferences.getString("Source_Name", "GogoAnime");
            if (Source.equals("Hanime")) {

                id = String.valueOf(HID);

                holder.episode_name.setText("1");
            } else {
                JSONObject firstElement = episodes.getJSONObject(position);
                name = firstElement.getString("number");
                id = firstElement.getString("id");

                holder.episode_name.setText(name);
            }


            holder.episode_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, VideoPlayer.class);

                    if (Source.equals("Hanime")) {
                        id = String.valueOf(HID);
                        intent.putExtra("ID", id);
                        intent.putExtra("Length", 1);
                    }


                    if (!Source.equals("Hanime")) {
                        intent.putExtra("Title", Title);
                        intent.putExtra("current_episode", position);
                    }
                    activity.startActivity(intent);


                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int getItemCount() {
        SharedPreferences sharedpreferences = activity.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String Source = sharedpreferences.getString("Source_Name", "GogoAnime");
        if (Source.equals("Hanime")) {
            return 1;
        } else {
            return episodes.length();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView episode_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            episode_name = itemView.findViewById(R.id.episode_name);
        }
    }
}
