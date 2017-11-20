package br.com.zone.fragment;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import br.com.zone.R;

import br.com.zone.adapter.CardAdapter;
import br.com.zone.entities.cardObject;
import java.util.ArrayList;
import java.util.List;


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

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        final CardAdapter adapter = new CardAdapter(cardList,this.getContext());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                View inflaterView = getActivity().getLayoutInflater().inflate(R.layout.novatarefa_activity, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(inflaterView);

                final EditText desc = (EditText) inflaterView.findViewById(R.id.novaTarefa_descri);
                final EditText name = (EditText) inflaterView.findViewById(R.id.novaTarefa_nome);
                final EditText horario = (EditText) inflaterView.findViewById(R.id.novaTarefa_data);
                final EditText data = (EditText) inflaterView.findViewById(R.id.novaTarefa_time);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        cardList.add(new cardObject(desc.getText().toString(),name.getText().toString(),horario.getText().toString(),data.getText().toString(),"true", "data", 0));

                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // Clicked 'Cancel'
                            }
                        });
                builder.show();

            }
        });
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
