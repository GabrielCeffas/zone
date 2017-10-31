package br.com.zone.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.zone.R;

public class semanalFragment extends Fragment {


    public semanalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semanal, container, false);
        GridLayoutManager gridLayout = new GridLayoutManager(getActivity(), 2);


        return view;
    }

}
