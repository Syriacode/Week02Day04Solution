package example.org.test.week02day04homework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_FOR_MAIN = 427;
    public static final String KEY_SHARED_PREF = "shared_pref";
    public static final String KEY_LAST_ENTERED_NAME = "last_name";

    TextView tvNameDisplay;
    TextView tvAddressDisplay;
    TextView tvCityDisplay;
    TextView tvStateDisplay;
    TextView tvZipCodeDisplay;
    TextView tvPhoneNumberDisplay;
    TextView tvEmailAddressDisplay;

    SharedPreferences sharedPreferences;
    //Car Database Declaration
    UserDatabaseHelper userDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(KEY_SHARED_PREF, MODE_PRIVATE);
        //instantiate the car Database
        userDatabaseHelper = new UserDatabaseHelper(this);
        bindViews();
    }
    public void bindViews() {
        tvNameDisplay = findViewById(R.id.tvName);

    }
    public void populateTextViews(@NonNull User userInfoToPopulate) {
        tvNameDisplay.setText(userInfoToPopulate.getName());
        tvAddressDisplay.setText(userInfoToPopulate.getAddress());
        tvCityDisplay.setText(userInfoToPopulate.getCity());
        tvStateDisplay.setText(userInfoToPopulate.getState());
        tvZipCodeDisplay.setText(userInfoToPopulate.getZipCode());
        tvPhoneNumberDisplay.setText(userInfoToPopulate.getPhoneNumber());
        tvEmailAddressDisplay.setText(userInfoToPopulate.getEmailAddress());
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnStartForResult:
                Intent explicitIntent = new Intent(this, DataEntryActivity.class);
                startActivityForResult(explicitIntent, REQUEST_CODE_FOR_MAIN);
                break;

        }
    }
    public void saveUserNameToSharedPref(@NonNull User user) {
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putString(KEY_LAST_ENTERED_NAME, user.getName());
        sharedPrefEditor.commit();
    }
    public void saveAndLogCarInSharedPref(@NonNull User user) {
        //Get old values saved in pref
        String name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");

        //Log the old values
        Log.d(
                "TAG",
                "saveAndLogUserInSharedPref: IN SHARED PREF: name = " + name );

        //Save new values to shared pref
        saveUserNameToSharedPref(user);

        //get new values
        name = sharedPreferences.getString(KEY_LAST_ENTERED_NAME, "NO VALUE ENTERED");

        //log the new values
        Log.d(
                "TAG",
                "saveAndLogUserInSharedPref: IN SHARED PREF: name = " + name );
    }

    public void saveUserToDBandSeeLog(@NonNull User user){
        userDatabaseHelper.insertUserIntoDatabase(user);
        ArrayList<User> userList = userDatabaseHelper.getAllCarsFromDatabase();
        for(User currentUser : userList) {
            Log.d("TAG", currentUser.toString());
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // null check the intent sent back with result
        if(data != null) {
            //Get bundle from Intent
            Bundle resultBundle = data.getExtras();
            if(resultBundle != null){
                User resultUser = resultBundle.getParcelable(DataEntryActivity.KEY_USER_RESULT);
                if(resultUser != null) {
                    saveAndLogCarInSharedPref(resultUser);//save and log make model in shared pref
                    saveUserToDBandSeeLog(resultUser);//save car to db and print list of all cars in db
                    populateTextViews(resultUser); //populate the views
                }

            }
        }
    }

}
