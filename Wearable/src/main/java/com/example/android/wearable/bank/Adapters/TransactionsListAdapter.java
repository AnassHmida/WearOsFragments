package com.example.android.wearable.bank.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.wearable.bank.Model.Transaction.Transactions;
import com.example.android.wearable.jumpingjack.R;


import java.util.List;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.ViewHolder> {

    private List<Transactions> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ItemClickListener mClickListener;

    private static final String TAG = "MyAdapter";

    public TransactionsListAdapter(Context context,List<Transactions> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }
public void SetTransactionData(List<Transactions> data){
    this.mData = data;
    notifyDataSetChanged();
}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.transactions_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String title = mData.get(position).getAmount();
        holder.myTextView.setText(mData.get(position).getAmount());
        holder.myDescription.setText(mData.get(position).getOperation());
        if(mData.get(position).getAmount().contains("-")){
            holder.myTextView.setTextColor(context.getResources().getColor(R.color.flat_red));
        }
        holder.myTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+ title);
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myDescription;
        private CardView cardframe;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.row_title);
//            cardframe = itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(this);
            myDescription = itemView.findViewById(R.id.row_description);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}