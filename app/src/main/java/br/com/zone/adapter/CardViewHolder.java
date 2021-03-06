package br.com.zone.adapter;


import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.zone.R;


public class CardViewHolder extends RecyclerView.ViewHolder{

    final TextView description;
    final TextView title;
    final TextView horario;
    final ImageButton button;
    final TextView data;
    final TextView dataText;
    final ImageView dataIcon;
    final Button concluido;
    public CardViewHolder(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.cardDescription);
        title = (TextView) itemView.findViewById(R.id.cardTitle);
        horario = (TextView) itemView.findViewById(R.id.cardHorario);
        button = (ImageButton) itemView.findViewById(R.id.MiniMaxButton);
        data = (TextView) itemView.findViewById(R.id.cardData);
        dataText = (TextView) itemView.findViewById(R.id.dataText);
        dataIcon = (ImageView) itemView.findViewById(R.id.cardDataimage);
        concluido = (Button) itemView.findViewById(R.id.doneButton);
    }

}
