package ly.generalassemb.drewmahrt.cursoradapterdemo;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ly.generalassemb.drewmahrt.cursoradapterdemo.setup.GroceryItem;

/**
 * Created by drewmahrt on 12/29/15.
 */
public class ExampleSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = ExampleSQLiteOpenHelper.class.getCanonicalName();
    ExampleSQLiteOpenHelper dbInstance;

//    private ExampleSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    public ExampleSQLiteOpenHelper getInstance(Context context){
//        if (dbInstance == null) {
//            new ExampleSQLiteOpenHelper(context, "db", );
//        }
//    }

    private static ExampleSQLiteOpenHelper DB;

    public static ExampleSQLiteOpenHelper getInstance(Context context) {
        if (DB == null) {
            DB = new ExampleSQLiteOpenHelper(context);
        }
        return DB;
    }

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TEST_DB";
    public static final String EXAMPLE_LIST_TABLE_NAME = "EXAMPLE_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_ITEM_DESCRIPTION = "DESCRIPTION";

    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_NAME,COL_ITEM_DESCRIPTION};

    private static final String CREATE_EXAMPLE_LIST_TABLE =
            "CREATE TABLE " + EXAMPLE_LIST_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_ITEM_DESCRIPTION + " TEXT )";


    private ExampleSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EXAMPLE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXAMPLE_LIST_TABLE_NAME);
        this.onCreate(db);
    }

    public List<GroceryItem> getExampleList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(EXAMPLE_LIST_TABLE_NAME, // a. table
                EXAMPLE_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        List<GroceryItem> items = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int i_id = cursor.getColumnIndex((ExampleSQLiteOpenHelper.COL_ID));
            int i_name = cursor.getColumnIndex((ExampleSQLiteOpenHelper.COL_ITEM_NAME));
            int i_desc = cursor.getColumnIndex((ExampleSQLiteOpenHelper.COL_ITEM_DESCRIPTION));

            int id = cursor.getInt(i_id);
            String name = cursor.getString(i_name);
            String description = cursor.getString(i_desc);

            items.add(new GroceryItem(id, name, description));
            cursor.moveToNext();
        }
        return items;
    }

    public String getDescription(int position) {

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT " + COL_ITEM_DESCRIPTION + " FROM " + EXAMPLE_LIST_TABLE_NAME
                + " WHERE " + COL_ID + " = '" + position + "';";
        Cursor cursor = db.rawQuery(sql, null);

        cursor.moveToFirst();
        String description = cursor.getString(0);

        return description;
    }


}
