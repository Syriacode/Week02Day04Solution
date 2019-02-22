package example.org.test.week02day04homework;

import android.util.Log;

public class UserDatabaseContract {
    public static final String DATABASE_NAME = "User_db";
    public static final int DATABASE_VERSION = 1;
    //Database table name
    public static final String TABLE_NAME = "users_db";
    //Columns in database names
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_ZIPCODE = "zipCode";
    public static final String COLUMN_PHONENUMBER= "phoneNumber";
    public static final String COLUMN_EMAILADDRESS = "EMAILADDRESS";
    public static final String COLUMN_ID = "id";
    public static String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        //Query to create Table
        queryBuilder.append("CREATE TABLE ");
        queryBuilder.append(TABLE_NAME);
        queryBuilder.append(" ( ");
        //Must have unique primary key
        queryBuilder.append(COLUMN_ID);
        queryBuilder.append(" ");
        queryBuilder.append(" INT NONNULL IDENTITY PRIMARY KEY, ");
        //Add rest of the columns
        queryBuilder.append(COLUMN_NAME);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ADDRESS);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_CITY);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_STATE);
        queryBuilder.append(" TEXT, ");
        queryBuilder.append(COLUMN_ZIPCODE);
        queryBuilder.append(" TEXT )");
        queryBuilder.append(COLUMN_PHONENUMBER);
        queryBuilder.append(" TEXT )");
        queryBuilder.append(COLUMN_EMAILADDRESS);
        queryBuilder.append(" TEXT )");


        //Log the query so we can check for correctness
        Log.d("TAG", "createQuery: " + queryBuilder.toString());

        return queryBuilder.toString();
    }
    public static String getAllUsersQuery() {
        return "SELECT * FROM " + TABLE_NAME;
    }

    public static String getOneUserByName(int id) {
        return String.format("SELECT * FROM %s WHERE %s = \"%d\"", TABLE_NAME, COLUMN_NAME, id);
    }
}
