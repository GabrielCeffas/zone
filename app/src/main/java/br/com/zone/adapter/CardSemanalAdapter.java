package br.com.zone.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import br.com.zone.R;
import br.com.zone.entities.cardObject;

public class CardSemanalAdapter extends RecyclerView.Adapter{

    private List<cardObject> testes;
    private Context context;
    View view;

    public CardSemanalAdapter(List<cardObject> teste, Context context) {
        this.testes = teste;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context)
                .inflate(R.layout.cardsemanal_list_layout, parent, false);
        CardSemanalViewHolder holder = new CardSemanalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CardSemanalViewHolder holder = new CardSemanalViewHolder(view);
        final cardObject object = testes.get(position);
        holder.title.setText(object.getTitle());
        holder.description.setText(object.getDescription());
        holder.horario.setText(object.getHorario());
        holder.data.setText(object.getData());
        holder.button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showPopupMenu(holder.button, position);
            }
        });
    }

    private void showPopupMenu(View view,int position) {
        // inflate menu
        PopupMenu popup = new PopupMenu(view.getContext(),view );
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.cardmenu, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popup.show();
    }

    @Override
    public int getItemCount() {
        return testes.size();
    }
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        private int position;

        public MyMenuItemClickListener(int positon) {
            this.position=positon;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            final DatabaseHandler db = new DatabaseHandler(context);
            switch (menuItem.getItemId()) {

                case R.id.action_delete:
                    Log.d("TESTANDO", Integer.toString(position));
                    db.deleteCard(testes.get(position).getId());
                    testes.remove(position);
                    notifyDataSetChanged();
                    return true;
                case R.id.action_edit:

                    View inflaterView = LayoutInflater.from(context).inflate(R.layout.novatarefa_activity, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(inflaterView);

                    final EditText desc = (EditText) inflaterView.findViewById(R.id.novaTarefa_descri);
                    final EditText name = (EditText) inflaterView.findViewById(R.id.novaTarefa_nome);
                    final Button horario = (Button) inflaterView.findViewById(R.id.horarioButton);
                    final Button data = (Button) inflaterView.findViewById(R.id.dataButton);
                    desc.setText(testes.get(position).getDescription());
                    name.setText(testes.get(position).getTitle());
                    horario.setText(testes.get(position).getHorario());
                    data.setText(testes.get(position).getData());

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            testes.get(position).setDescription(desc.getText().toString());
                            testes.get(position).setTitle(name.getText().toString());
                            testes.get(position).setHorario(horario.getText().toString());
                            testes.get(position).setData(data.getText().toString());
                            db.updateCard(testes.get(position));
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Clicked 'Cancel'
                        }
                    });
                    builder.show();
                    return true;

                default:
            }
            return false;
        }
    }
}
