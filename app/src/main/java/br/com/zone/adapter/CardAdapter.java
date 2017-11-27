package br.com.zone.adapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import br.com.zone.R;
import br.com.zone.entities.cardObject;

import java.util.Calendar;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter{

    private List<cardObject> cardList;
    private List<cardObject> doneCardsList;
    private Context context;
    View view;

    public CardAdapter(List<cardObject> list, List<cardObject> doneList, Context context) {
        this.cardList = list;
        this.doneCardsList = doneList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context)
                .inflate(R.layout.cardsemanal_list_layout, parent, false);


        CardViewHolder holder = new CardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final CardViewHolder holder = (CardViewHolder) viewHolder;
        final cardObject object = cardList.get(position);
        if(object.getTipo().equals("Diario")){
            holder.data.setVisibility(View.INVISIBLE);
            holder.dataText.setVisibility(View.INVISIBLE);
            holder.dataIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.data.setText(object.getData());
        }
        holder.title.setText(object.getTitle());
        holder.description.setText(object.getDescription());
        holder.horario.setText(object.getHorario());
        holder.button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showPopupMenu(holder.button, position);
            }
        });
        holder.concluido.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(object.getTipo().equals("Diario")) {
                    doneCardsList.add(object);
                    cardList.remove(object);
                    object.setIsShown(1);
                    final DatabaseHandler db = new DatabaseHandler(context);
                    db.updateCard(object);
                }
                else{
                    cardList.remove(object);
                }
                notifyDataSetChanged();
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
        return cardList.size();
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
                    db.deleteCard(cardList.get(position).getId());
                    cardList.remove(position);
                        notifyDataSetChanged();
                    return true;
                case R.id.action_edit:
                    View inflaterView = new View(context);
                    if(cardList.get(position).getTipo().equals("Diario")){
                        inflaterView = LayoutInflater.from(context).inflate(R.layout.novatarefa_activity, null);
                    }
                    else{
                        inflaterView = LayoutInflater.from(context).inflate(R.layout.novatarefasemanal_activity, null);
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(inflaterView);

                    final EditText desc = (EditText) inflaterView.findViewById(R.id.novaTarefa_descri);
                    final EditText name = (EditText) inflaterView.findViewById(R.id.novaTarefa_nome);
                    final Button horario = (Button) inflaterView.findViewById(R.id.horarioButton);
                    final Button data = (Button) inflaterView.findViewById(R.id.dataButton);
                    horario.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            final Calendar c = Calendar.getInstance();
                            int hour = c.get(Calendar.HOUR_OF_DAY);
                            int minute = c.get(Calendar.MINUTE);


                            TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                                    new TimePickerDialog.OnTimeSetListener() {

                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay,
                                                              int minute) {
                                            String hourString;
                                            String minuteString;
                                            if(hourOfDay<10)
                                                hourString = "0"+hourOfDay;
                                            else
                                                hourString = "" + hourOfDay;

                                            if (minute < 10)
                                                minuteString = "0" + minute;
                                            else
                                                minuteString = "" + minute;

                                            horario.setText(hourString + ":" + minuteString);
                                        }
                                    }, hour, minute, true);
                            timePickerDialog.show();
                        }
                    });
                    if(cardList.get(position).getTipo().equals("Semanal")){
                        data.setOnClickListener(new View.OnClickListener(){
                            public void onClick(View v){
                                final Calendar c = Calendar.getInstance();
                                int startyear = c.get(Calendar.YEAR);
                                int startmonth = c.get(Calendar.MONTH);
                                int startday = c.get(Calendar.DAY_OF_MONTH);
                                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                                        new DatePickerDialog.OnDateSetListener(){
                                            @Override
                                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                                data.setText(dayOfMonth + "/" + month + "/" + year);
                                            }
                                        }, startyear,startmonth,startday);
                                datePickerDialog.show();
                            }

                        });
                        data.setText(cardList.get(position).getData());
                    }

                    desc.setText(cardList.get(position).getDescription());
                    name.setText(cardList.get(position).getTitle());
                    horario.setText(cardList.get(position).getHorario());
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Clicked 'Cancel'
                        }
                    });
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            if(!desc.getText().toString().equals("") && !name.getText().toString().equals("")){

                                cardList.get(position).setDescription(desc.getText().toString());
                                cardList.get(position).setTitle(name.getText().toString());
                                cardList.get(position).setHorario(horario.getText().toString());
                                if(cardList.get(position).getTipo().equals("Semanal"))
                                    cardList.get(position).setData(data.getText().toString());
                                db.updateCard(cardList.get(position));
                                notifyDataSetChanged();
                                dialog.dismiss();

                            }


                        }
                    });
                    return true;

                default:
            }
            return false;
        }
    }
}
