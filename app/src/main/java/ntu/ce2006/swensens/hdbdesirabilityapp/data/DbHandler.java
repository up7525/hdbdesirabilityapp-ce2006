package ntu.ce2006.swensens.hdbdesirabilityapp.data;

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

/**
 * Control class that handles data access to the SQLite database
 * @author Jonathan
 */

public class DbHandler extends SQLiteOpenHelper {
    /**
     * Database version
     */
    private static final int DATABASE_VER = 1;
    /**
     * Name of database to be stored
     */
    private static final String DATABASE_NAME = "local.db";
    /**
     * Defined name of table in database
     */
    private static final String TABLE_QUERY = "queries";
    /**
     * Main column name
     */
    private static final String KEY_ID = "id";
    /**
     * Column name that will store the Query object
     */
    private static final String QUERY = "query_data";

    /**
     * Constructor of this control class
     */
    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    /**
     * Will be run on construction of this object.
     * Will create a database if there is no existing one.
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "CREATE TABLE " + TABLE_QUERY + "( " + KEY_ID + " INTEGER PRIMARY KEY," + QUERY + " TEXT" + ")";
        db.execSQL(create_query);
    }

    /**
     * Method to upgrade an existing database to a newer version
     * @param db database
     * @param oldVersion old version number
     * @param newVersion new version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUERY);
        // Create tables again
        onCreate(db);
    }

    /**
     * Method to add a new query to the database
     * @param row Row to add query to
     * @param query Query to be added
     */
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

    /**
     * Method to get a single query from the database
     * @param id Row to get query from
     * @return Query to be returned
     */
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

    /**
     * Method to get all queries in the database
     * @return List of all queries
     */
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

    /**
     * Update an existing query in the database
     * @param row Row of query to be updated
     * @param query New query to be updated with
     */
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

    /**
     * Method to remove a query from database
     * @param row Row to remove query from
     */
    // Deleting single query
    public void deleteQuery(int row) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY, KEY_ID + "=?", new String[] { Integer.toString(row) });
        db.close();
    }

    /**
     * Method to find out how many queries are in the database
     * @return number of queries in database
     */
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

    /**
     * Method to remove all queries from database, effectively a wipe
     */
    //delete ALL queries
    public void deleteAllQuery() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY,null,null);
        db.close();
    }
}
