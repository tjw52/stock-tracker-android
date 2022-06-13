package com.example.stocktracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StocksAdapter extends RecyclerView.Adapter<StocksAdapter.StocksViewHolder> {

    private Context mCtx;
    private List<Stock> stockList;

    public StocksAdapter(Context mCtx, List<Stock> stockList) {
        this.mCtx = mCtx;
        this.stockList = stockList;
    }

    @Override
    public StocksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_stocks, parent, false);
        return new StocksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StocksViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.textViewName.setText(stock.getName());
        holder.textViewSymbol.setText(stock.getSymbol());
        holder.textViewQty.setText("Quantity: " + Integer.toString(stock.getQuantity()));
        holder.textViewPrice.setText("Rate: " + Double.toString(stock.getPricePerStock()) + " " + stock.getCurrency());

        Double total = stock.getQuantity() * stock.getPricePerStock();
        total = total * stock.getCurrencyConversionRate();
        holder.textViewTotal.setText(Double.toString(total) + " CHF");
        //String total = t.getValue().toString();
        //holder.textViewTotal.setText(total);
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    class StocksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewSymbol, textViewQty, textViewPrice, textViewTotal;

        public StocksViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewSymbol = itemView.findViewById(R.id.textViewSymbol);
            textViewQty = itemView.findViewById(R.id.textViewQty);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Stock stock = stockList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateStockActivity.class);
            intent.putExtra("stock", stock);

            mCtx.startActivity(intent);

            /*Intent intent = new Intent(mCtx, UpdateStockActivity.class);
            intent.putExtra("stock", stock);

            mCtx.startActivity(intent);*/
        }



    }

}
