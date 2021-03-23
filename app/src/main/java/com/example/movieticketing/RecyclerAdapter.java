package com.example.movieticketing;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private Resources resources;

    private String[] movieNames;
    private String[] movieTimes;
    private String[] ageRatings;
    private TypedArray movieCovers;
    private String[] videoIDs;

    public RecyclerAdapter(Context context){
        this.context = context;
        resources = context.getResources();
        movieNames = resources.getStringArray(R.array.movieNames);
        movieTimes = resources.getStringArray(R.array.movieTimes);
        ageRatings = resources.getStringArray(R.array.ageRatings);
        movieCovers = resources.obtainTypedArray(R.array.movieCovers);
        videoIDs = resources.getStringArray(R.array.videoIDs);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ViewHolder vh = new ViewHolder(v, this);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.coverImage.setImageResource(movieCovers.getResourceId(position, R.drawable.ic_launcher_background));
        holder.titleTextView.setText(movieNames[position]);
        holder.timeTextView.setText(movieTimes[position]);
        holder.ageRating.setText(ageRatings[position]);
        //holder.ratingBar.getProgressDrawable().setColorFilter(resources.getColor(R.color.colorPrimaryDark, context.getTheme()), PorterDuff.Mode.SRC_ATOP);
        System.out.println(position);
    }

    @Override
    public int getItemCount() {
        return movieNames.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView titleTextView;
        TextView timeTextView;
        TextView ageRating;
        CardView cardBase;

        public ViewHolder(@NonNull final View itemView, final RecyclerAdapter adapter) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.coverImage);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            timeTextView = itemView.findViewById(R.id.tiemTextView);
            ageRating = itemView.findViewById(R.id.ageRestriction);
            cardBase = itemView.findViewById(R.id.cardBase);

            //todo: youtube, new screen
            coverImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("test");
                    Intent intent = new Intent(context, PlayerActivity.class);
                    intent.putExtra("id", videoIDs[getAdapterPosition()]);
                    context.startActivity(intent);
                }
            });
        }
    }
}