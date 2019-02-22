package example.org.test.week02day04homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

class DataEntryActivity extends AppCompatActivity {
    public static final String KEY_USER_RESULT = "User_result";
    public static final int RESULT_CODE = 656;

    EditText etNameDisplay;
    EditText etAddressDisplay;
    EditText etCityDisplay;
    EditText etStateDisplay;
    EditText etZipCodeDisplay;
    EditText etPhoneNumberDisplay;
    EditText etEmailAddressDisplay;
    Intent sentIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__data__entry);
        sentIntent = getIntent(); //gets intent that started the activity
        bindViews();
    }
    public void bindViews() {
        etNameDisplay = findViewById(R.id.etName);
        etAddressDisplay = findViewById(R.id.etAddress);
        etCityDisplay = findViewById(R.id.etCity);
        etStateDisplay = findViewById(R.id.etState);
        etZipCodeDisplay = findViewById(R.id.etZipCode);
        etPhoneNumberDisplay = findViewById(R.id.etPhoneNumber);
        etEmailAddressDisplay = findViewById(R.id.etEmailAddress);


    }
    public User generateUserObjectFromInput() {
        User returnUser = new User();

        //Set-up Car object
        returnUser.setName(
                etNameDisplay.getText() != null ? etNameDisplay.getText().toString() : "");
        returnUser.setAddress(
                etAddressDisplay.getText() != null ? etAddressDisplay.getText().toString() : "");
        returnUser.setCity(
                etCityDisplay.getText() != null ? etCityDisplay.getText().toString() : "");
        returnUser.setState(
                etStateDisplay.getText() != null ? etStateDisplay.getText().toString() : "");
        returnUser.setZipCode(
                etZipCodeDisplay.getText() != null ? etZipCodeDisplay.getText().toString() : "");
        returnUser.setPhoneNumber(
                etPhoneNumberDisplay.getText() != null ? etPhoneNumberDisplay.getText().toString() : "");
        returnUser.setEmailAddress(
                etZipCodeDisplay.getText() != null ? etZipCodeDisplay.getText().toString() : "");

        return returnUser;
    }
    public void onClick(){}
    public void onClick(View view) {
        User userResult = generateUserObjectFromInput();
        Bundle bundleOfTheCarResult = new Bundle();
        bundleOfTheCarResult.putParcelable(KEY_USER_RESULT, userResult);
        sentIntent.putExtras(bundleOfTheCarResult);
        setResult(RESULT_CODE, sentIntent);
        finish();
    }


}
