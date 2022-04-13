package com.example.stocktracker;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Stock.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract StockDao stockDao();
}
