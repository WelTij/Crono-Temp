package com.example.tvwearos;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tvwearos.databinding.FragmentParcialBinding;
import com.example.tvwearos.placeholder.PlaceholderContent;
import com.example.tvwearos.placeholder.PlaceholderContent.ParcialholderItem;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ParcialholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPArcialRecyclerViewAdapter extends RecyclerView.Adapter<MyPArcialRecyclerViewAdapter.ViewHolder> {

    private List<ParcialholderItem> mValues;

    public MyPArcialRecyclerViewAdapter(List<ParcialholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentParcialBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(String.valueOf(mValues.get(position).indice));
        holder.mContentView.setText(mValues.get(position).parcial);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void refill(List<PlaceholderContent.ParcialholderItem> items)
    {
        mValues = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public ParcialholderItem mItem;

        public ViewHolder(FragmentParcialBinding binding) {
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