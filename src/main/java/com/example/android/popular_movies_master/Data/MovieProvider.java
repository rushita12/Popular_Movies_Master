package com.example.android.popular_movies_master.Data;

import android.net.Uri;



import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.InexactContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;


/**
 * Created by Chinky on 7/3/2016.
 */


@ContentProvider(authority = MovieProvider.AUTHORITY, database = Databases.class)

public class MovieProvider {
    private MovieProvider() {}

    public static final String AUTHORITY = "com.example.android.popular_movies_master";
    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    interface Path {
        String FAVORITESNAME = "favorites";
    }

    private static Uri buildUri(String... paths) {
        Uri.Builder builder = BASE_CONTENT_URI.buildUpon();
        for(String s : paths) {
            builder.appendPath(s);
        }
        return builder.build();
    }

    @TableEndpoint(table = Databases.FAVORITES)
    public static class Favorites {
        @ContentUri(
                path = Path.FAVORITESNAME,
                type = "vnd.android.cursor.dir/favorite",
                defaultSort = FavoritesColumns._ID + " ASC")
        public static final Uri CONTENT_URI = buildUri(Path.FAVORITESNAME);

        @InexactContentUri(
                name = "MOVIE_ID",
                path = Path.FAVORITESNAME + "/*",
                type = "vnd.android.cursor.item/favorite",
                whereColumn = FavoritesColumns.MOVIE_ID,
                pathSegment = 1)
        public static Uri withId(String id) {
            return buildUri(Path.FAVORITESNAME, id);
        }
    }
}
