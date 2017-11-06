package br.com.zone.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.zone.R;


public class SongViewHolder extends RecyclerView.ViewHolder{

    final TextView description;
    final TextView title;
    final TextView horario;

    public SongViewHolder(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.testedescription);
        title = (TextView) itemView.findViewById(R.id.testetitle);
        horario = (TextView) itemView.findViewById(R.id.horario);
    }

}
