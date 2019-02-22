package example.org.test.week02day04homework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_NAME;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_ID;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_ADDRESS;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_STATE;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_CITY;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_ZIPCODE;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_PHONENUMBER;
import static example.org.test.week02day04homework.UserDatabaseContract.COLUMN_EMAILADDRESS;
import static example.org.test.week02day04homework.UserDatabaseContract.DATABASE_NAME;
import static example.org.test.week02day04homework.UserDatabaseContract.DATABASE_VERSION;
import static example.org.test.week02day04homework.UserDatabaseContract.TABLE_NAME;






public class UserDatabaseHelper extends SQLiteOpenHelper {


    public UserDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UserDatabaseContract.createQuery());
    }
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onCreate(database);
    }
    public long insertUserIntoDatabase(@NonNull User user) {
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_NAME, user.getName());
        contentValues.put(COLUMN_ADDRESS, user.getAddress());
        contentValues.put(COLUMN_CITY, user.getCity());
        contentValues.put(COLUMN_STATE, user.getState());
        contentValues.put(COLUMN_ZIPCODE, user.getZipCode());
        contentValues.put(COLUMN_PHONENUMBER, user.getPhoneNumber());
        contentValues.put(COLUMN_EMAILADDRESS, user.getEmailAddress());

        //insert the car into the table using contentValues
        return writeableDatabase.insert(TABLE_NAME, null, contentValues);
    }
    public ArrayList<User> getAllCarsFromDatabase() {
        ArrayList<User> returnUserList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Get results from query and hold in cursor(iterable object for database operations
        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getAllUsersQuery(), null);
        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
                String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
                String zipcode = cursor.getString(cursor.getColumnIndex(COLUMN_ZIPCODE));
                String phonenumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER));
                String emailaddress = cursor.getString(cursor.getColumnIndex(COLUMN_EMAILADDRESS));


                //add to list
                returnUserList.add(new User(name, address, city, state, zipcode, phonenumber, emailaddress, id));
            } while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnUserList;
    }
    public User getUserByName(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        User returnUser = new User();
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(UserDatabaseContract.getOneUserByName(id), null);
        if(cursor.moveToFirst()){
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String address = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
            String city = cursor.getString(cursor.getColumnIndex(COLUMN_CITY));
            String state = cursor.getString(cursor.getColumnIndex(COLUMN_STATE));
            String zipcode = cursor.getString(cursor.getColumnIndex(COLUMN_ZIPCODE));
            String phonenumber = cursor.getString(cursor.getColumnIndex(COLUMN_PHONENUMBER));
            String emailaddress = cursor.getString(cursor.getColumnIndex(COLUMN_EMAILADDRESS));

            returnUser = new User(name, address, city, state, zipcode,phonenumber, emailaddress, idFromDB);
        }
        cursor.close();
        return returnUser;
    }



}
