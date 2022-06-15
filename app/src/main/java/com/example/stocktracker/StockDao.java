package com.example.stocktracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StockDao {

    @Query("SELECT * FROM stock ORDER BY name")
    List<Stock> getAll();

    @Insert
    void insert(Stock stock);

    @Delete
    void delete(Stock stock);

    @Update
    void update(Stock stock);

}
