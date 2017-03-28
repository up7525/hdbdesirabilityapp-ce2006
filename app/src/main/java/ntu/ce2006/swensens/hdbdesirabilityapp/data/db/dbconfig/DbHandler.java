package ntu.ce2006.swensens.hdbdesirabilityapp.data.db.dbconfig;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ntu.ce2006.swensens.hdbdesirabilityapp.pin.Pin;
import ntu.ce2006.swensens.hdbdesirabilityapp.search.query.Query;

/**
 * Created by Jonathan on 27-Mar-17.
 */




public class DbHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "OverallDB.db";
    // Table names
    private static final String TABLE_QUERY = "queries";
    private static final String TABLE_PIN = "pins";
    // Common column names
    private static final String KEY_ID = "id";
    // TABLE_QUERY column names
    private static final String QUERY_LOC = "locations";
    private static final String QUERY_SIZE = "size";
    private static final String QUERY_PRICE = "price";
    private static final String QUERY_AMEN = "amenities";
    // TABLE_PIN column names
    private static final String PIN_CODE = "postal code";
    private static final String PIN_DESC = "desc";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUERY_TABLE = "CREATE TABLE " + TABLE_QUERY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + QUERY_LOC + " TEXT,"
                + QUERY_SIZE + " TEXT" + QUERY_PRICE + " TEXT," +
                QUERY_AMEN + " TEXT," + ")";
        String CREATE_PINS_TABLE = "CREATE TABLE " + TABLE_PIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + PIN_CODE + " INTEGER,"
                + PIN_DESC + " TEXT" + ")";
        db.execSQL(CREATE_QUERY_TABLE);
        db.execSQL(CREATE_PINS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PIN);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new query
    void addQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // TODO talk about Query attributes (convert to String[])
        //values.put(KEY_ID, query.convertArrayToString(query.getId_key()));
        //values.put(QUERY_LOC, query.convertArrayToString(query.getLocationFilters()));
        //values.put(QUERY_SIZE, query.convertArrayToString(query.getSizeFilters()));
        //values.put(QUERY_PRICE, query.convertArrayToString(query.getPriceFilters()));
        //values.put(QUERY_AMEN, query.convertArrayToString(query.getAmenitiesFilters()));
        // Inserting Row
        db.insert(TABLE_QUERY, null, values);
        db.close(); // Closing database connection
    }

    //Adding new Pin
    void addPin(Pin pin) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, pin.getId_DB());
        values.put(PIN_CODE, pin.getPostalcode());
        values.put(PIN_DESC, pin.getDesc());
        // Inserting Row
        db.insert(TABLE_PIN, null, values);
        db.close(); // Closing database connection
    }

    // Getting single query
    Query getQuery(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_QUERY, new String[] { KEY_ID,
                        QUERY_LOC, QUERY_SIZE, QUERY_PRICE, QUERY_AMEN }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Query.Builder queryBuilder = new Query.Builder();
        /*
        queryBuilder.idDB(KEY_ID, Query.convertArrayToString(cursor.getString(0)));
        queryBuilder.locations(Query.convertStringToArray(cursor.getString(1)));
        queryBuilder.size(Query.convertStringToArray(cursor.getString(2)));
        queryBuilder.price(Integer.parseInt(cursor.getString(3)));
        queryBuilder.amenities(Query.convertStringToArray(cursor.getString(4))); */
        Query query = queryBuilder.build();
        return query;
    }

    // Getting single pin
    Pin getPin(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PIN, new String[] { KEY_ID,
                        PIN_CODE, PIN_DESC }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Pin pin = new Pin(cursor.getInt(0),cursor.getInt(1), cursor.getString(2));
        // return pin
        return pin;
    }

    // Getting All Queries
    public List<Query> getAllQueries() {
        List<Query> queryList = new ArrayList<Query>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUERY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Query.Builder queryBuilder = new Query.Builder();
                /*
                queryBuilder.idDB(KEY_ID, Query.convertArrayToString(cursor.getString(0)));
                queryBuilder.locations(Query.convertStringToArray(cursor.getString(1)));
                queryBuilder.size(Query.convertStringToArray(cursor.getString(2)));
                queryBuilder.price(Integer.parseInt(cursor.getString(3)));
                queryBuilder.amenities(Query.convertStringToArray(cursor.getString(4)));
                Query query = queryBuilder.build();*/

                // Adding to list
                //queryList.add(query);
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

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Pin pin = new Pin(cursor.getInt(0),cursor.getInt(1), cursor.getString(2));
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
        //values.put(KEY_ID, query.convertArrayToString(query.getId_key()));
        //values.put(QUERY_LOC, query.convertArrayToString(query.getLocationFilters()));
        //values.put(QUERY_SIZE, query.convertArrayToString(query.getSizeFilters()));
        //values.put(QUERY_PRICE, query.convertArrayToString(query.getPriceFilters()));
        //values.put(QUERY_AMEN, query.convertArrayToString(query.getAmenitiesFilters()));

        // updating row
        return db.update(TABLE_QUERY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(query.getId_key()) });
    }

    // Updating pin
    public int updatePin(Pin pin) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, pin.getId_DB());
        values.put(PIN_CODE, pin.getPostalcode());
        values.put(PIN_DESC, pin.getDesc());

        // updating row
        return db.update(TABLE_QUERY, values, KEY_ID + " = ?",
                new String[] { String.valueOf(pin.getId_DB()) });
    }

    // Deleting single query
    public void deleteQuery(Query query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUERY, KEY_ID + " = ?",
                new String[] { String.valueOf(query.getId_key()) });
        db.close();
    }

    // Deleting single pin
    public void deletePin(Pin pin) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PIN, KEY_ID + " = ?",
                new String[] { Integer.toString(pin.getId_DB()) });
        db.close();
    }


    // Getting query Count
    public int getQueryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_QUERY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Getting pin Count
    public int getPinCount() {
        String countQuery = "SELECT  * FROM " + TABLE_PIN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
