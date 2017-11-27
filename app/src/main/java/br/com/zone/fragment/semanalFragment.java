package br.com.zone.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.zone.R;
import br.com.zone.adapter.CardAdapter;
import br.com.zone.adapter.CardSemanalAdapter;
import br.com.zone.adapter.DatabaseHandler;
import br.com.zone.entities.cardObject;

public class semanalFragment extends Fragment {
    List<cardObject> cardList = new ArrayList<>();
    List<cardObject> doneList = new ArrayList<>();


    public semanalFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_semanal, container, false);

        getActivity().setTitle("Zone");
        final DatabaseHandler db = new DatabaseHandler(getActivity());
        cardList = db.getSemanalCards();
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerSemanal);
        final CardAdapter adapter = new CardAdapter(cardList,doneList,this.getContext());
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter.notifyDataSetChanged();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                View inflaterView = getActivity().getLayoutInflater().inflate(R.layout.novatarefasemanal_activity, null);

                final EditText desc = (EditText) inflaterView.findViewById(R.id.novaTarefa_descri);
                final EditText name = (EditText) inflaterView.findViewById(R.id.novaTarefa_nome);
                final Button horario = (Button) inflaterView.findViewById(R.id.horarioButton);
                final Button data = (Button) inflaterView.findViewById(R.id.dataButton);
                horario.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final Calendar c = Calendar.getInstance();
                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);


                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
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
                data.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        final Calendar c = Calendar.getInstance();
                        int startyear = c.get(Calendar.YEAR);
                        int startmonth = c.get(Calendar.MONTH);
                        int startday = c.get(Calendar.DAY_OF_MONTH);
                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener(){

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                data.setText(dayOfMonth + "/" + month + "/" + year);
                            }
                        }, startyear,startmonth,startday);
                        datePickerDialog.show();
                    }

                });


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setView(inflaterView);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.setNegativeButton(android.R.string.cancel, null);

                final AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(!desc.getText().toString().equals("")
                                && !name.getText().toString().equals("")
                                && !horario.getText().toString().equals("Selecionar Horario")
                                && !data.getText().toString().equals("Selecionar Data")){

                            cardObject card = new cardObject(
                                    desc.getText().toString(),
                                    name.getText().toString(),
                                    horario.getText().toString(),
                                    "Semanal", data.getText().toString(), 0, 0);

                            db.addCard(card);
                            cardList.add(card);
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();

                        }


                    }
                });

            }
        });
        return view;
    }
    public void daily(Context ctx){
        final DatabaseHandler db = new DatabaseHandler(getActivity());
        Calendar cal = Calendar.getInstance();
        int currentDay = cal.get(Calendar.DAY_OF_YEAR);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        String date = dayOfMonth + "/" + month + "/" + year;

        SharedPreferences sharedPreferences= ctx.getSharedPreferences("appInfo", 0);
        int dayofYear = sharedPreferences.getInt("dayofYear", 0);

        if(dayofYear != currentDay){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("dayofYear", currentDay);
            editor.commit();


            for(int i =0; i< cardList.size();i++){
                if(cardList.get(i).getData().equals(date)){
                    cardList.remove(i);
                }
            }
        }
    }
}
