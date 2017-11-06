package br.com.zone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.zone.R;
import br.com.zone.entities.cardObject;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter{

    private List<cardObject> testes;
    private Context context;

    public CardAdapter(List<cardObject> teste, Context context) {
        this.testes = teste;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.card_list_layout, parent, false);
        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        CardViewHolder holder = (CardViewHolder) viewHolder;
        cardObject object = testes.get(position);
        holder.title.setText(object.getTitle());
        holder.description.setText(object.getDescription());
        holder.horario.setText(object.getHorario());

    }



    @Override
    public int getItemCount() {
        return testes.size();
    }

}
