package br.com.zone.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.zone.entities.cardObject;

/**
 * Created by Gabriel-PC on 20/11/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CardDB";
    private static final String TABLE_CARDS = "Cards";
    private static final String KEY_DESC = "Description";
    private static final String KEY_NAME = "Name";
    private static final String KEY_HORARIO = "Horario";
    private static final String KEY_DURACACO = "Duracao";
    private static final String KEY_ISSHOWN = "IsShown";
    private static final String KEY_ISSEMANAL = "IsSemanal";
    private static final String KEY_DATA = "Data";
    private static final String KEY_ID = "id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_CARDS + "(" +  KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_DESC
                + " TEXT," + KEY_DURACACO + " TEXT," + KEY_HORARIO + " TEXT," +  KEY_ISSEMANAL
                + " TEXT," + KEY_ISSHOWN + " TEXT," + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
        onCreate(db);
    }
    public void addCard(cardObject card){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, card.getTitle());
        values.put(KEY_DURACACO, card.getduração());
        values.put(KEY_HORARIO, card.getHorario());
        values.put(KEY_DESC, card.getDescription());
        values.put(KEY_DATA, card.getData());
        values.put(KEY_ISSEMANAL, card.getIsSemanal());
        values.put(KEY_ISSHOWN, "TRUE");
        db.insert(TABLE_CARDS, null, values);
        db.close();


    }
    




}
