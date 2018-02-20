package com.androidframework.feature.movielist;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.androidframework.R;
import com.androidframework.pojo.Movie;
import com.androidframework.util.UiUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yendang on 2/19/18.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    public interface MovieListClickCallback {
        void onMovieItemClick(Movie movie);
    }

    private List<Movie> mMovieList;
    private MovieListClickCallback mMovieClickCallback;
    private Context context;
    private int numOfCol;

    public MovieListAdapter(Context context, int numOfCol) {
        this.context = context;
        this.numOfCol = numOfCol;
    }

    public void setmMovieList(List<Movie> mMovieList) {
        this.mMovieList = mMovieList;
        notifyDataSetChanged();
    }

    public void setmMovieClickCallback(MovieListClickCallback mMovieClickCallback) {
        this.mMovieClickCallback = mMovieClickCallback;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, int position) {


        Movie movie = mMovieList.get(position);
        String posterUrl = holder.imgMovieBanner.getContext().getString(R.string.tmdb_image_url) +
                context.getString(R.string.image_size_780) + movie.getPosterUrl();

        Picasso.with(context).load(posterUrl)
                .resize(UiUtil.getPixelFromDp(50), UiUtil.getPixelFromDp(50))
                .centerCrop()
                .into(holder.imgMovieBanner);

        holder.itemView.setOnClickListener(v -> mMovieClickCallback.onMovieItemClick(movie));

    }

    @Override
    public int getItemCount() {
        return mMovieList == null ? 0 : mMovieList.size();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgMovieBanner)
        ImageView imgMovieBanner;

        @BindView(R.id.cardView)
        CardView cardView;

        public MovieListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);


            int widthItem = (UiUtil.getDisplayWidth() - (numOfCol + 1) * UiUtil.getPixelFromDp(5)) / numOfCol;
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(widthItem, widthItem);
            cardView.setLayoutParams(layoutParams);
            cardView.requestLayout();
        }
    }
}
