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

import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "local.db";
    // Table names
    private static final String TABLE_QUERY = "queries";
    // Common column names
    private static final String KEY_ID = "id";
    // TABLE_QUERY column names
    private static final String QUERY = "query_data";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "CREATE TABLE " + TABLE_QUERY + "( " + KEY_ID + " INTEGER PRIMARY KEY," + QUERY + " TEXT" + ")";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUERY);
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

    // Updating query
    public int updateQuery(int row, Query query) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        values.put(KEY_ID, row);
        values.put(QUERY, gson.toJson(query));
        // updating row
        return db.update(TABLE_QUERY, values, KEY_ID + " = ?",
                new String[] { Integer.toString(row) });
    }

    // Deleting single query
    public void deleteQuery(int row) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY, KEY_ID + "=?", new String[] { Integer.toString(row) });
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

    //delete ALL queries
    public void deleteAllQuery() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY,null,null);
        db.close();
    }
}
