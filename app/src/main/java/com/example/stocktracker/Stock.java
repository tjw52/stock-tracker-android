package com.example.stocktracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Stock {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "symbol")
    private String symbol;

    @ColumnInfo(name = "currency")
    private String currency;

    @ColumnInfo(name = "currency_value")
    private Double currencyValue;

    @ColumnInfo(name = "value")
    private Double value;

    @ColumnInfo(name = "quantity")
    private int quantity;

    /*
     * Getters and Setters
     */
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getCurrency() { return currency; }

    public void setCurrency(String currency) { this.currency = currency; }

    public Double getCurrencyValue() { return currencyValue; }

    public void setCurrencyValue(Double currencyValue) { this.currencyValue = currencyValue; }

    public Double getValue() { return value; }

    public void setValue(Double value) { this.value = value; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int currencyValue) { this.quantity = quantity; }



}
