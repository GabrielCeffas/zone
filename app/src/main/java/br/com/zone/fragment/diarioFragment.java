package br.com.zone.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidmusicplayer.R;
import br.com.zone.adapter.SongAdapter;
import br.com.zone.entities.SongObject;

import java.util.ArrayList;
import java.util.List;

public class diarioFragment extends Fragment {

    public diarioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song, container, false);

        getActivity().setTitle("Zone");
        RecyclerView songRecyclerView = (RecyclerView)view.findViewById(R.id.song_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        songRecyclerView.setLayoutManager(linearLayoutManager);
        songRecyclerView.setHasFixedSize(true);

        SongAdapter mAdapter = new SongAdapter(getActivity(), getTestData());
        songRecyclerView.setAdapter(mAdapter);
        return view;
    }

    public List<SongObject> getTestData() {
        List<SongObject> recentSongs = new ArrayList<SongObject>();

        return recentSongs;
    }
}
