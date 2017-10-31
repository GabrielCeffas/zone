package br.com.zone.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.zone.R;

import br.com.zone.adapter.SongAdapter;
import br.com.zone.entities.SongObject;

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

        SongAdapter mAdapter = new SongAdapter(getActivity(), getTestData());
        return view;
    }

    public List<SongObject> getTestData() {
        List<SongObject> recentSongs = new ArrayList<SongObject>();

        return recentSongs;
    }
}
