package br.com.zone.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidmusicplayer.R;

public class novaTarefaFragment extends Fragment {

    public novaTarefaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.novatarefa_fragment, container, false);

        getActivity().setTitle("Zone");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        return view;
    }

}