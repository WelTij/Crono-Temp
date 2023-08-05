package com.example.tvwearos;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tvwearos.databinding.FragmentTemporizadorBinding;
import com.example.tvwearos.placeholder.PlaceholderTemporizadorContent.TemporizadorholderItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link TemporizadorholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTemporizadorRecyclerViewAdapter extends RecyclerView.Adapter<MyTemporizadorRecyclerViewAdapter.ViewHolder> {

    private List<TemporizadorholderItem> mValues;

    public MyTemporizadorRecyclerViewAdapter(List<TemporizadorholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentTemporizadorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).indice));
        holder.mContentView.setText(mValues.get(position).temporizador);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void refill(List<TemporizadorholderItem> items)
    {
        mValues = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public TemporizadorholderItem mItem;

        public ViewHolder(FragmentTemporizadorBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}