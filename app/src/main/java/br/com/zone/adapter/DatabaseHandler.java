package br.com.zone.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.zone.entities.cardObject;

/**
 * Created by Gabriel-PC on 20/11/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "CardDB1";
    private static final String TABLE_CARDS = "Cards";
    private static final String KEY_DESC = "Description";
    private static final String KEY_NAME = "Name";
    private static final String KEY_HORARIO = "Horario";
    private static final String KEY_ISSHOWN = "IsShown";
    private static final String KEY_TIPO = "Tipo";
    private static final String KEY_DATA = "Data";
    private static final String KEY_ID = "id";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE " + TABLE_CARDS +
                        "(" +
                        KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        KEY_DESC + " TEXT," +
                        KEY_NAME + " TEXT," +
                        KEY_HORARIO + " TEXT," +
                        KEY_TIPO + " TEXT," +
                        KEY_DATA + " TEXT," +
                        KEY_ISSHOWN + " TEXT" +
                        ")";
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
        values.put(KEY_DESC, card.getDescription());
        values.put(KEY_NAME, card.getTitle());
        values.put(KEY_HORARIO, card.getHorario());
        values.put(KEY_TIPO, card.getTipo());
        values.put(KEY_DATA, card.getData());
        values.put(KEY_ISSHOWN, card.getIsShown());
        db.insert(TABLE_CARDS, null, values);
    }

     public cardObject getCard(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CARDS,
                new String[]{KEY_ID, KEY_DESC, KEY_NAME, KEY_HORARIO, KEY_TIPO, KEY_DATA, KEY_ISSHOWN},
                KEY_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        cardObject card = new cardObject(cursor.getString(1),cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(0));
        return card;
    }

    public List<cardObject> getSemanalCards(){
        List<cardObject> cardList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CARDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(4).equals("Semanal")) {
                    cardObject contact = new cardObject(cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(0));

                    cardList.add(contact);
                }
            } while (cursor.moveToNext());
        }

        return cardList;

    }
    public List<cardObject> getDiarioCards(){
        List<cardObject> cardList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CARDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if(cursor.getString(4).equals("Diario")) {
                    cardObject contact = new cardObject(cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6), cursor.getInt(0));

                    cardList.add(contact);
                }
            } while (cursor.moveToNext());
        }

        return cardList;

    }

    public int getCardCount(){
        String countQuery = "SELECT * FROM " + TABLE_CARDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
        return cursor.getCount();
    }

    public int updateCard(cardObject card){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, card.getTitle());
        values.put(KEY_HORARIO, card.getHorario());
        values.put(KEY_DESC, card.getDescription());
        values.put(KEY_DATA, card.getData());
        values.put(KEY_TIPO, card.getTipo());
        values.put(KEY_ISSHOWN, "TRUE");
        return db.update(TABLE_CARDS, values, KEY_ID + " = ?", new String[]{String.valueOf(card.getId())});
    }
    public void deleteCard(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARDS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }





}
