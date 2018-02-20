package com.androidframework.feature.moviedetail;


import android.app.Fragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidframework.R;
import com.androidframework.core.BaseFragment;
import com.androidframework.pojo.Movie;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends BaseFragment {


    private static final String MOVIE_ID = "MOVIE_ID";


    @BindView(R.id.layout_movie_details)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.movie_detail_year)
    TextView mMovieYear;
    @BindView(R.id.movie_detail_plot)
    TextView mMoviePlot;
    @BindView(R.id.movie_detail_thumbnail)
    ImageView mThumbnail;
    @BindView(R.id.movie_detail_rating)
    TextView mMovieRating;
    @BindView(R.id.movie_detail_popularity)
    TextView mMoviePopularity;
    @BindView(R.id.movie_detail_votes)
    TextView mMovieVote;
    @BindView(R.id.circularProgressBar)
    CircularProgressBar mCircularProgressBar;
    @BindView(R.id.coordinated_layout)
    CoordinatorLayout mCoordinatedLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout appBarLayout;
    @BindView(R.id.backdrop)
    ImageView backdrop;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.layout_display_info)
    RelativeLayout layoutDisplayInfo;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.error_msg)
    TextView mErrorTextView;

    private MovieDetailViewModel viewModel;
    private int movieId;

    public static MovieDetailFragment newInstance(int movieId) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putInt(MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movieId = getArguments().getInt(MOVIE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel.class);

        fetchMovieDetail();
        observeMovieDetail();
    }

    private void fetchMovieDetail() {


        viewModel.fetchMovieDetail(movieId);
    }

    private void observeMovieDetail() {

        viewModel.getMovie().observe(this, movie -> displayMovieInfo(movie));

    }

    // TODO: 2/20/18 Just for demo, still not fill full data.
    private void displayMovieInfo(Movie mMovieResult) {

        if (mMovieResult != null) {
            //Image path
            String imagePath = getString(R.string.tmdb_image_url) +
                    getString(R.string.image_size_780) + mMovieResult.getPosterUrl();

            //Backdrop Image URL
            String imagePathBackDrop = getString(R.string.tmdb_image_url) +
                    getString(R.string.image_size_780)
                    + mMovieResult.getBackdropPath();

            Picasso.with(getActivity())
                    .load(imagePath)
                    .into(mThumbnail);

            Picasso.with(getActivity())
                    .load(imagePathBackDrop)
                    .into(backdrop);

            float rating = mMovieResult.getVoteAverage().floatValue() * 10;
            float popularity = mMovieResult.getPopularity().intValue();


            mMoviePlot.setText(mMovieResult.getOverview());
            mMovieYear.setText("2017");
            mMovieRating.setText(String.valueOf(mMovieResult.getVoteAverage()));
            mMoviePopularity.setText(String.valueOf(popularity));
            mMovieVote.setText(String.valueOf(mMovieResult.getVoteCount()));
            mCircularProgressBar.setProgressWithAnimation(rating);

            appBarLayout.setTitle(mMovieResult.getTitle());


        } else {
            mErrorTextView.setVisibility(View.VISIBLE);
            mErrorTextView.setText(getResources().getString(R.string.error_no_results));
        }
    }
}
