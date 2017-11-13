package br.com.zone.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.zone.R;

import br.com.zone.adapter.CardAdapter;
import br.com.zone.entities.cardObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class diarioFragment extends Fragment {
    List<cardObject> cardList = new ArrayList<>();

    public diarioFragment() {
    }
    public void addCard(cardObject card){
        cardList.add(card);
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
        cardList.add(new cardObject("Descrição teste", "Título Teste", "15:30"));
        cardList.add(new cardObject("Descrição teste", "Título Teste", "15:30"));
        cardList.add(new cardObject("Descrição teste", "Título Teste", "15:30"));


        Context ctx = getActivity();



        recyclerView.setAdapter(new CardAdapter(cardList, getActivity()));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        Context ctx = getActivity();


    }
    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Context ctx = getActivity();


    }


}
