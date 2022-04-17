package com.example.stocktracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateStockActivity extends AppCompatActivity {

    private EditText editTextName, editTextSymbol, editTextCurrencyConversionRate, editTextPricePerStock, editTextQuantity;
    private Spinner spCurrencies;
    private TextView tvTotalValue;

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
        tvTotalValue = findViewById(R.id.textViewTotalValue);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                saveStock();
            }
        });

    }

    private void saveStock() {
        final String sName = editTextName.getText().toString().trim();
        final String sSymbol = editTextSymbol.getText().toString().trim();
        //final String sCurrency = editTextCurrency.getText().toString().trim();
        final String sCurrency = spCurrencies.getSelectedItem().toString();

        final String sCurrencyValue = editTextCurrencyConversionRate.getText().toString().trim();
        final Double dCurrencyValue;
        final String sValue = editTextPricePerStock.getText().toString().trim();
        final Double dValue;
        final String sQuantity = editTextQuantity.getText().toString().trim();
        final int iQuantity;

        // Validate fields
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
        }

        // Save new entry in DB
        class SaveStock extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                // creating new stock
                Stock stock = new Stock();
                stock.setName(sName);
                stock.setSymbol(sSymbol);
                stock.setCurrency(sCurrency);
                stock.setCurrencyConversionRate(dCurrencyValue);
                stock.setPricePerStock(dValue);
                stock.setQuantity(iQuantity);

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

        SaveStock ss = new SaveStock();
        ss.execute();

    }

}
