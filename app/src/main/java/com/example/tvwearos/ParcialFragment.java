package com.example.tvwearos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvwearos.placeholder.PlaceholderContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ParcialFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    MyPArcialRecyclerViewAdapter recyclerView;

    public static final List<PlaceholderContent.ParcialholderItem> ITEMS = new ArrayList<PlaceholderContent.ParcialholderItem>();
    public static final List<PlaceholderContent.ParcialholderItem> ITEMS_REVERSO = new ArrayList<PlaceholderContent.ParcialholderItem>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ParcialFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ParcialFragment newInstance(int columnCount) {
        ParcialFragment fragment = new ParcialFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parcial_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = new MyPArcialRecyclerViewAdapter(ITEMS_REVERSO);
            RecyclerView rcv = (RecyclerView) view;
            rcv.setLayoutManager(new LinearLayoutManager(context));
            rcv.setAdapter(recyclerView);
        }
        return view;
    }

    public void agregarTiempoParcial(String tiempoParcial){
        int indice= ITEMS.size()+1;
        PlaceholderContent.ParcialholderItem item = new PlaceholderContent.ParcialholderItem(indice,tiempoParcial);
        ITEMS.add(item);
        ITEMS_REVERSO.clear();
        for(int i= ITEMS.size(); i>0; i--){
            ITEMS_REVERSO.add(ITEMS.get(i-1));
        }

        recyclerView.refill(ITEMS_REVERSO);
    }

    public void resetParciales(){
        ITEMS.clear();
        recyclerView.refill(ITEMS);
    }
}