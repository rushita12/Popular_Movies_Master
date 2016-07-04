package com.example.android.popular_movies_master.Data;


import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Chinky on 7/3/2016.
 */
@Database(version = Databases.VERSION)
public final class Databases {
    private Databases(){}

    public static final int VERSION = 1;

    @Table(FavoritesColumns.class)
    public static final String FAVORITES = "favorites";
}
