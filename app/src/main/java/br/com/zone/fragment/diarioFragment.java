package br.com.zone.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.zone.R;

import br.com.zone.adapter.CardAdapter;
import br.com.zone.entities.cardObject;

import java.util.ArrayList;
import java.util.List;

public class diarioFragment extends Fragment {

    public diarioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diario, container, false);

        getActivity().setTitle("Zone");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mStackLevel = 0;
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);
                DialogFragment newFragment = novaTarefaFragment.newInstance(mStackLevel);
                newFragment.show(ft, "Adicionar Tarefa");
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        List<cardObject> exemplo = new ArrayList<>();
        exemplo.add(new cardObject("Descrição teste", "Título Teste", "15:30"));
        exemplo.add(new cardObject("Descrição teste", "Título Teste", "15:30"));
        exemplo.add(new cardObject("Descrição teste", "Título Teste", "15:30"));

        recyclerView.setAdapter(new CardAdapter(exemplo, getActivity()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }



}
