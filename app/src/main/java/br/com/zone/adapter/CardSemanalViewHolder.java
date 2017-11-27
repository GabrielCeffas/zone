package br.com.zone.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.zone.R;


public class CardSemanalViewHolder extends RecyclerView.ViewHolder{

    final TextView description;
    final TextView title;
    final TextView horario;
    final TextView data;
    final ImageButton button;

    public CardSemanalViewHolder(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.cardDescription);
        title = (TextView) itemView.findViewById(R.id.cardTitle);
        horario = (TextView) itemView.findViewById(R.id.cardHorario);
        data = (TextView) itemView.findViewById(R.id.cardData);
        button = (ImageButton) itemView.findViewById(R.id.MiniMaxButton);
    }

}
