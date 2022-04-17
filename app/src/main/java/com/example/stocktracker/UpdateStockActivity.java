package com.example.stocktracker;

import static java.sql.DriverManager.println;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateStockActivity extends AppCompatActivity {

    private EditText editTextName, editTextSymbol, editTextCurrencyConversionRate, editTextPricePerStock, editTextQuantity;
    private Spinner spCurrencies;
    private TextView tvTitle, tvTotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_stock);

        editTextName = findViewById(R.id.editTextName);
        editTextSymbol = findViewById(R.id.editTextSymbol);
        //editTextCurrency = findViewById(R.id.editTextCurrency);
        spCurrencies = findViewById(R.id.spCurrencies);
        editTextCurrencyConversionRate = findViewById(R.id.editTextCurrencyConversionRate);
        editTextPricePerStock = findViewById(R.id.editTextPricePerStock);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        tvTitle = findViewById(R.id.textViewTitle);
        tvTotalValue = findViewById(R.id.textViewTotalValue);

        final Stock stock = (Stock) getIntent().getSerializableExtra("stock");
        Log.d("lifecycle", "onCreate: " + stock);

        // If stock equals null then a new stock is created in the activity, otherwise a stock is being updated
        if (stock == null) {
            tvTitle.setText("Create stock");
        } else {
            tvTitle.setText("Update stock");
            loadStock(stock);
        }

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                if (stock == null) {
                    saveStock();
                } else {
                    updateStock(stock);
                }
            }
        });

    }

    private void loadStock(Stock stock) {
        editTextName.setText(stock.getName());
        editTextSymbol.setText(stock.getSymbol());
        if (stock.getCurrency().equals("CHF")) {
            spCurrencies.setSelection(0);
        } else if (stock.getCurrency().equals("EUR")) {
            spCurrencies.setSelection(1);
        } else if (stock.getCurrency().equals("USD")) {
            spCurrencies.setSelection(2);
        }
        editTextCurrencyConversionRate.setText(stock.getCurrencyConversionRate().toString());
        editTextPricePerStock.setText(stock.getPricePerStock().toString());
        editTextQuantity.setText(String.valueOf(stock.getQuantity()));

    }

    private void updateStock(final Stock stock) {

        class UpdateStock extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                stock.setName(editTextName.getText().toString().trim());
                stock.setSymbol(editTextSymbol.getText().toString().trim());
                stock.setCurrency(spCurrencies.getSelectedItem().toString());
                stock.setCurrencyConversionRate(Double.parseDouble(editTextCurrencyConversionRate.getText().toString().trim()));
                stock.setPricePerStock(Double.parseDouble(editTextPricePerStock.getText().toString().trim()));
                stock.setQuantity(Integer.parseInt(editTextQuantity.getText().toString().trim()));

                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .stockDao()
                        .update(stock);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
            }

        }

        if (stockIsValid()) {
            UpdateStock us = new UpdateStock();
            us.execute();
        }


    }

    private void saveStock() {
        // Save new entry in DB
        class SaveStock extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                // creating new stock
                Stock stock = new Stock();
                stock.setName(editTextName.getText().toString().trim());
                stock.setSymbol(editTextSymbol.getText().toString().trim());
                stock.setCurrency(spCurrencies.getSelectedItem().toString());
                stock.setCurrencyConversionRate(Double.parseDouble(editTextCurrencyConversionRate.getText().toString().trim()));
                stock.setPricePerStock(Double.parseDouble(editTextPricePerStock.getText().toString().trim()));
                stock.setQuantity(Integer.parseInt(editTextQuantity.getText().toString().trim()));

                // add to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .stockDao()
                        .insert(stock);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }

        }
        if (stockIsValid()) {
            SaveStock ss = new SaveStock();
            ss.execute();
        }

    }

    private boolean stockIsValid() {

        final String sName = editTextName.getText().toString().trim();
        final String sSymbol = editTextSymbol.getText().toString().trim();
        final String sCurrency = spCurrencies.getSelectedItem().toString();

        final String sCurrencyValue = editTextCurrencyConversionRate.getText().toString().trim();
        final String sValue = editTextPricePerStock.getText().toString().trim();
        final String sQuantity = editTextQuantity.getText().toString().trim();

        // Validate fields
        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return false;
        }

        if (sSymbol.isEmpty()) {
            editTextSymbol.setError("Symbol required");
            editTextSymbol.requestFocus();
            return false;
        }

        if (sCurrency.isEmpty()) {
            //spCurrencies.setError("Currency required");
            spCurrencies.requestFocus();
            return false;
        }

        if (sCurrencyValue.isEmpty()) {
            editTextCurrencyConversionRate.setError("Conversion rate required");
            editTextCurrencyConversionRate.requestFocus();
            return false;
        }

        if (sValue.isEmpty()) {
            editTextPricePerStock.setError("Price per stock required");
            editTextPricePerStock.requestFocus();
            return false;
        }

        if (sQuantity.isEmpty()) {
            editTextQuantity.setError("Quantity required");
            editTextQuantity.requestFocus();
            return false;
        }

        return true;
    }

}


// Deprecated

/*final String sName = editTextName.getText().toString().trim();
        final String sSymbol = editTextSymbol.getText().toString().trim();
        final String sCurrency = spCurrencies.getSelectedItem().toString();

        final String sCurrencyValue = editTextCurrencyConversionRate.getText().toString().trim();
        final Double dCurrencyValue;
        final String sValue = editTextPricePerStock.getText().toString().trim();
        final Double dValue;
        final String sQuantity = editTextQuantity.getText().toString().trim();
        final int iQuantity;

        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (sSymbol.isEmpty()) {
            editTextSymbol.setError("Symbol required");
            editTextSymbol.requestFocus();
            return;
        }

        if (sCurrency.isEmpty()) {
            //spCurrencies.setError("Currency required");
            spCurrencies.requestFocus();
            return;
        }

        if (sCurrencyValue.isEmpty()) {
            editTextCurrencyConversionRate.setError("Conversion rate required");
            editTextCurrencyConversionRate.requestFocus();
            return;
        } else {
            dCurrencyValue = Double.parseDouble(sCurrencyValue);
        }

        if (sValue.isEmpty()) {
            editTextPricePerStock.setError("Price per stock required");
            editTextPricePerStock.requestFocus();
            return;
        } else {
            dValue = Double.parseDouble(sValue);
        }

        if (sQuantity.isEmpty()) {
            editTextQuantity.setError("Quantity required");
            editTextQuantity.requestFocus();
            return;
        } else {
            iQuantity = Integer.parseInt(sQuantity);
        }*/
