package com.example.tvwearos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvwearos.placeholder.PlaceholderTemporizadorContent;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class TemporizadorFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    MyTemporizadorRecyclerViewAdapter recyclerView;

    public static final List<PlaceholderTemporizadorContent.TemporizadorholderItem> ITEMS = new ArrayList<PlaceholderTemporizadorContent.TemporizadorholderItem>();
    public static final List<PlaceholderTemporizadorContent.TemporizadorholderItem> ITEMS_REVERSO = new ArrayList<PlaceholderTemporizadorContent.TemporizadorholderItem>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TemporizadorFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TemporizadorFragment newInstance(int columnCount) {
        TemporizadorFragment fragment = new TemporizadorFragment();
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
        View view = inflater.inflate(R.layout.fragment_temporizador_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = new MyTemporizadorRecyclerViewAdapter(ITEMS_REVERSO);
            RecyclerView rcv = (RecyclerView) view;
            rcv.setLayoutManager(new LinearLayoutManager(context));
            rcv.setAdapter(recyclerView);
        }
        return view;
    }

    public void agregarTiempoTemporizador(String tiempoTemporizador){
        int indice= ITEMS.size()+1;
        PlaceholderTemporizadorContent.TemporizadorholderItem item = new PlaceholderTemporizadorContent.TemporizadorholderItem(indice,tiempoTemporizador);
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