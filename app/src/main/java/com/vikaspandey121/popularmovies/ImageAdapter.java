package com.vikaspandey121.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.vikaspandey121.popularmovies.model.Movie;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final Movie[] mMovies;
    private final Context mContext;
    View.OnClickListener listener;

    public ImageAdapter(Context mContext, Movie[] mMovies) {
        this.mMovies = mMovies;
        this.mContext = mContext;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ViewHolder(ImageView v) {
            super(v);
            mImageView = v;
        }
    }
    @NonNull
    @Override
    // Create new views (Invoked by the Layout Manager)
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Create a new view
        ImageView v = (ImageView) LayoutInflater.from(parent.getContext ())
                .inflate (R.layout.image_thumb_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    // Replace the contents of a view (Invoked by the layout manager)
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get()
                .load(mMovies[position].getPosterPath())
                .fit()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into((ImageView) holder.mImageView.findViewById (R.id.image_view));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, MovieDetails.class);
            intent.putExtra("movie", mMovies[position]);
            mContext.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        if (mMovies == null || mMovies.length == 0) {
            return -1;
        }

        return mMovies.length;
    }
}
