package com.example.android.popular_movies_master;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if(savedInstanceState == null) {

            Bundle bundle = new Bundle();
            bundle.putParcelable(MovieDetailActivityFragment.ARG_MOVIE_TAG,
                    getIntent().getExtras().getParcelable(MovieDetailActivityFragment.ARG_MOVIE_TAG));

            MovieDetailActivityFragment mf = new MovieDetailActivityFragment();
            mf.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, mf)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


}
