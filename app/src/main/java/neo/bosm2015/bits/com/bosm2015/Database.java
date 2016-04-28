package neo.bosm2015.bits.com.bosm2015;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sports.db";
    public static final String TABLE_SPORTS = "sports";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_RESULT = "result";

    String CREATE_SPORTS_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_SPORTS + "("
            + COLUMN_ID + " TEXT, " + COLUMN_NAME
            + " TEXT, " + COLUMN_DESC + " TEXT, " + COLUMN_RESULT + " TEXT)";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_SPORTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPORTS);
        onCreate(db);

    }

    public void addSport(String id, String name, String description, String result){

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESC, description);
        values.put(COLUMN_RESULT, result);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SPORTS, null, values);
        //db.close();
    }

    public void updateSport(String id, String name, String description, String result){

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESC, description);
        values.put(COLUMN_RESULT, result);
        SQLiteDatabase db = this.getWritableDatabase();
/*		String query = "UPDATE " + EVENTS_EVENTNEW + " SET " + FAVOURITE + " = " + favourite + " WHERE "
					   + ID + " = " + id;
*/
        db.update(TABLE_SPORTS, values, COLUMN_ID + "=?", new String[]{id});
    }

    public void addSport(Sport sport){
        if(checkIfExists(TABLE_SPORTS, sport.getID())){
            updateSport(sport.getID(),sport.getName(), sport.getDesc(), sport.getResult());
        }
        else{
            addSport(sport.getID(),sport.getName(), sport.getDesc(), sport.getResult());
        }
    }

    public boolean checkIfExists(String table_name, String id){
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =
                db.query(table_name, new String[]{COLUMN_ID}, COLUMN_ID + " =?", new String[] {id}, null, null, null);
        if(cursor.getCount()!=0)
            result = true;
        cursor.close();
        return result;
    }

    public String getSportColumn(String sport, boolean desc) {

        String column, value = "";
        SQLiteDatabase db = this.getWritableDatabase();

        if(desc)
            column = COLUMN_DESC;
        else
            column = COLUMN_RESULT;

        String query = "SELECT " + column + " FROM " + TABLE_SPORTS + " WHERE " +
                COLUMN_NAME + " = '" + sport+"'";

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            value = cursor.getString(cursor.getColumnIndex(column));
        }

        cursor.close();

        return value;

    }
}
