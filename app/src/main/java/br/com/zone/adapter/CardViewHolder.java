package br.com.zone.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.zone.R;


public class CardViewHolder extends RecyclerView.ViewHolder{

    final TextView description;
    final TextView title;
    final TextView horario;

    public CardViewHolder(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.cardDescription);
        title = (TextView) itemView.findViewById(R.id.cardTitle);
        horario = (TextView) itemView.findViewById(R.id.cardHorario);
    }

}
