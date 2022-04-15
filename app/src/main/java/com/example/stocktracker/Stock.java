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

    @ColumnInfo(name = "currency_conversion_rate")
    private Double currencyConversionRate;

    @ColumnInfo(name = "price_per_stock")
    private Double pricePerStock;

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

    public Double getCurrencyConversionRate() { return currencyConversionRate; }

    public void setCurrencyConversionRate(Double currencyConversionRate) { this.currencyConversionRate = currencyConversionRate; }

    public Double getPricePerStock() { return pricePerStock; }

    public void setPricePerStock(Double pricePerStock) { this.pricePerStock = pricePerStock; }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }


    public static Stock populateData() {
        Stock stock = new Stock();
        stock.setName("Apple");
        stock.setSymbol("AAPL");
        stock.setCurrency("USD");
        stock.setCurrencyConversionRate(0.95);
        stock.setPricePerStock(170.60);
        stock.setQuantity(55);
        return stock;
    }



}
