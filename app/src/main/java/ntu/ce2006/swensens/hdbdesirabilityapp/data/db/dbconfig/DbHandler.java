package ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan on 29-Mar-17.
 */

import ntu.ce2006.swensens.hdbdesirabilityapp.pin.Pin;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "local.db";
    // Table names
    private static final String TABLE_QUERY = "queries";
    private static final String TABLE_PIN = "pins";
    // Common column names
    private static final String KEY_ID = "id";
    // TABLE_QUERY column names
    private static final String QUERY = "query_data";
    // TABLE_PIN column names
    private static final String PIN = "pin_data";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "CREATE TABLE " + TABLE_QUERY + "( " + KEY_ID + " INTEGER PRIMARY KEY," + QUERY + " TEXT" + ")";
        String create_pin = "CREATE TABLE " + TABLE_PIN + "( " + KEY_ID + " INTEGER PRIMARY KEY," + PIN + " TEXT" + ")";
        db.execSQL(create_query);
        db.execSQL(create_pin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIN);
        // Create tables again
        onCreate(db);
    }

    // Adding new query
    public void addQuery(int row, Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        values.put(KEY_ID, row);
        values.put(QUERY, gson.toJson(query));
        // Inserting Row
        db.insertOrThrow(TABLE_QUERY, null, values);
        db.close(); // Closing database connection
    }

    //Adding new Pin
    public void addPin(int row, Pin pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        values.put(KEY_ID, row);
        values.put(PIN, gson.toJson(pin));
        // Inserting Row
        db.insertOrThrow(TABLE_PIN, null, values);
        db.close(); // Closing database connection
    }

    // Getting single query
    public Query getQuery(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_QUERY + " WHERE id = " + Integer.toString(id), null);
        if (cursor != null)
            cursor.moveToFirst();
        String output = cursor.getString(cursor.getColumnIndex(QUERY));
        Query query = gson.fromJson(output, Query.class);
        return query;
    }

    // Getting single pin
    public Pin getPin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Gson gson = new Gson();
        Cursor cursor = db.rawQuery("SELECT  * FROM " + TABLE_PIN + " WHERE id = " + Integer.toString(id), null);
        if (cursor != null)
            cursor.moveToFirst();
        String output = cursor.getString(cursor.getColumnIndex(PIN));
        Pin pin = gson.fromJson(output, Pin.class);

        return pin;
    }

    // Getting All Queries
    public List<Query> getAllQueries() {
        List<Query> queryList = new ArrayList<Query>();
        Gson gson = new Gson();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUERY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String output = cursor.getString(cursor.getColumnIndex(QUERY));
                Query query = gson.fromJson(output, Query.class);
                // Adding to list
                queryList.add(query);
            } while (cursor.moveToNext());
        }

        // return list
        return queryList;
    }

    // Getting All Pins
    public List<Pin> getAllPins() {
        List<Pin> pinList = new ArrayList<Pin>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PIN;
        Gson gson = new Gson();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String output = cursor.getString(cursor.getColumnIndex(PIN));
                Pin pin = gson.fromJson(output, Pin.class);
                // Adding to list
                pinList.add(pin);
            } while (cursor.moveToNext());
        }

        // return contact list
        return pinList;
    }

    // Updating query
    public int updateQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        values.put(QUERY, gson.toJson(query));
        // updating row
        return db.update(TABLE_QUERY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(query.getId_key()) });
    }

    // Updating pin
    public int updatePin(Pin pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(PIN, gson.toJson(pin));

        // updating row
        return db.update(TABLE_QUERY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pin.getId_DB()) });
    }

    // Deleting single query
    public void deleteQuery(Query query) {
        Gson gson = new Gson();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY, QUERY + "=?", new String[] { gson.toJson(query) });
        db.close();
    }

    // Deleting single pin
    public void deletePin(Pin pin) {
        Gson gson = new Gson();
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PIN, PIN + "=?", new String[] { gson.toJson(pin) });
        db.close();
    }


    // Getting query Count
    public int getQueryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_QUERY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Getting pin Count
    public int getPinCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    //delete ALL pins
    public void deleteAllPin() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PIN,null,null);
        db.close();
    }

    //delete ALL queries
    public void deleteAllQuery() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY,null,null);
        db.close();
    }
}