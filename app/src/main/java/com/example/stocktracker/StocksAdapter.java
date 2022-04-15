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

    public StocksAdapter(Context mCtx, List<Stock> taskList) {
        this.mCtx = mCtx;
        this.stockList = taskList;
    }

    @Override
    public StocksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_stocks, parent, false);
        return new StocksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StocksViewHolder holder, int position) {
        Stock t = stockList.get(position);
        holder.textViewName.setText(t.getName());
        holder.textViewQty.setText(Integer.toString(t.getQuantity()));

        holder.textViewTotal.setText("2500 CHF");
        //String total = t.getValue().toString();
        //holder.textViewTotal.setText(total);


    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    class StocksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewQty, textViewTotal;

        public StocksViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewQty = itemView.findViewById(R.id.textViewQty);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Stock stock = stockList.get(getAdapterPosition());

            /*Intent intent = new Intent(mCtx, UpdateStockActivity.class);
            intent.putExtra("stock", stock);

            mCtx.startActivity(intent);*/
        }

    }

}
