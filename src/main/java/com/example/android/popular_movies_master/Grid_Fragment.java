package com.example.android.popular_movies_master;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popular_movies_master.Data.FavoritesColumns;
import com.example.android.popular_movies_master.Data.MovieProvider;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */


public class Grid_Fragment extends Fragment implements FetchMovieData.callbackMovieFetch {

    private MovieAdapter globalMovies;
    private ArrayList<MovieObject> globalMoviesList;
    private GridView grid;
    private static int mPosition = GridView.INVALID_POSITION;


    private CallbackMain mCallback;

    public Grid_Fragment() {
    }

    public void updateMovies() {

        String sortUnit = getSortType();
        if(!sortUnit.equals(getString(R.string.favorites))) {
            new FetchMovieData(this).execute(sortUnit);
        } else {
            insertFavGlobalMovieList();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        updateMovies();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        globalMoviesList = new ArrayList<MovieObject>();

        if(savedInstanceState != null) {
            globalMoviesList = savedInstanceState.getParcelableArrayList("key");
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_grid_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menu_id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        grid = (GridView)rootView.findViewById(R.id.grid_movies);

        globalMovies = new MovieAdapter(
                getActivity(),
                R.layout.image_movie,
                R.id.movie_imageview,
                globalMoviesList
        );
        grid.setAdapter(globalMovies);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                mPosition = position;
                MovieObject movieObject = (MovieObject) adapterView.getItemAtPosition(position);

                try {
                    mCallback = (CallbackMain) getActivity();
                } catch (ClassCastException e) {
                    throw new ClassCastException(getActivity().toString() + " must implement CallbackMain interface");
                }

                if(mCallback != null) {
                    mCallback.onMovieSelect(movieObject);
                }
            }
        });

        if(savedInstanceState != null && savedInstanceState.containsKey("position")) {
            mPosition = savedInstanceState.getInt("position");
        }

        /*
        * smoother scrolling to where the user last clicked
         */
        if(mPosition != GridView.INVALID_POSITION || mPosition >= globalMovies.getMovieList().size()) {
            grid.smoothScrollToPosition(mPosition);
        }

        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle out) {
        out.putParcelableArrayList("key", globalMoviesList);
        if(mPosition != GridView.INVALID_POSITION) {
            out.putInt("position", mPosition);
        }
        super.onSaveInstanceState(out);
    }

    public String getSortType() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        return sharedPreferences.getString(getString(R.string.sortByKey), getString(R.string.popularity_desc));

    }

    /*
    * Query the database and update the current adapter instead of building a hooking a new CursorAdapter
    */
    public void insertFavGlobalMovieList() {
        Cursor cursor = getActivity().getContentResolver().query(MovieProvider.Favorites.CONTENT_URI, null, null, null, null);
        globalMovies.clear();
        if(cursor.getCount() > 0) {
            //begin inserting into globalMovie
            cursor.moveToFirst();
            do {
                MovieObject movieObject = new MovieObject(
                        cursor.getString(cursor.getColumnIndex(FavoritesColumns.MOVIE_TITLE)),
                        cursor.getString(cursor.getColumnIndex(FavoritesColumns.MOVIE_ID)),
                        cursor.getString(cursor.getColumnIndex(FavoritesColumns.POSTER_PATH)),
                        cursor.getString(cursor.getColumnIndex(FavoritesColumns.OVERVIEW)),
                        cursor.getDouble(cursor.getColumnIndex(FavoritesColumns.USER_RATING)),
                        cursor.getString(cursor.getColumnIndex(FavoritesColumns.RELEASE_DATE))
                );
                globalMovies.add(movieObject);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public void onResultFetch(ArrayList<MovieObject> movies) {
        if(movies != null) {
            globalMovies.clear();
            for(int i = 0; i < movies.size(); i++) {
                globalMovies.add(movies.get(i));
            }

            globalMoviesList = (ArrayList) globalMovies.getMovieList();
        }
    }

    public interface CallbackMain {
        void onMovieSelect(MovieObject m);
    }

}
