package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartparking.data.api.APIClient;
import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GenericResponse;
import com.example.smartparking.data.model.Card;
import com.example.smartparking.data.model.UsersPojo;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    private EditText firstName, lastName, age, address, cardNumber, securityCode, userPassword, nameOCard, postalCode;
    private Button registerBtn;
    DataBaseHelper myDataBaseHelper;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        myDataBaseHelper = new DataBaseHelper(this);
        setupRegister();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    // get the db and store the user info in it.
                    String fname = firstName.getText().toString();
                    String lname = lastName.getText().toString();
                    String upass = userPassword.getText().toString();
                    String dtime = Calendar.getInstance().getTime().toString();
                    long cardNo = Long.parseLong(cardNumber.getText().toString());
                    int sc = Integer.parseInt(securityCode.getText().toString());
                    int age_ = Integer.parseInt(age.getText().toString());
                    String addr_ = address.getText().toString();
                    String nameOnC = nameOCard.getText().toString();
                    int zip = Integer.parseInt(postalCode.getText().toString());
                    //adding to on device store
                    addData(fname,lname,upass,dtime,age_);

                    //adding to global store
                    Card c1 = new Card(cardNo, sc,nameOnC, zip);

                apiInterface = APIClient.getClient().create(APIInterface.class);
                UsersPojo user = new UsersPojo(fname+" "+lname,addr_, dtime, age_, c1, 0, "","" ,false,"");
                Call<GenericResponse> call2 = apiInterface.createUser(user);
                call2.enqueue(new Callback<GenericResponse>() {
                    @Override
                    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                        if (response.body().getSuccess().equals("1")) {
                            Toast.makeText(RegistrationActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GenericResponse> call, Throwable t) {

                    }
                });


                    firstName.setText("");
                    lastName.setText("");
                    userPassword.setText("");
                    address.setText("");
                    age.setText("");
                    cardNumber.setText("");
                    securityCode.setText("");




                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addData(String fname, String lname, String pass, String dtime,int a){
        Boolean check = false;
        if(a >=18){
           check = myDataBaseHelper.addData(fname,lname,pass,dtime);
        }
        if(check) {
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Unable to Register User, must be 18 or older", Toast.LENGTH_SHORT).show();
        }
    }
    private Boolean validate(){
        String fname = firstName.getText().toString();
        String lname = lastName.getText().toString();
        String upass = userPassword.getText().toString();

        if(fname.isEmpty() && upass.isEmpty() && lname.isEmpty() && existsUser(lname)){
            Toast.makeText(this, "Please enter all the details correctly/User with the Last name already exists.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void setupRegister(){
        firstName = (EditText) findViewById(R.id.ptFirstNameRegister);
        lastName = (EditText) findViewById(R.id.ptLastNameRegister);
        address = (EditText) findViewById(R.id.ptAddress);
        age = (EditText) findViewById(R.id.ptAge);
        cardNumber = (EditText) findViewById(R.id.ptCardNo);
        securityCode = (EditText) findViewById(R.id.ptSecurityCode);
        nameOCard = (EditText) findViewById(R.id.ptNameAOCard);
        postalCode = (EditText) findViewById(R.id.ptPostalCode);
        userPassword = (EditText) findViewById(R.id.etPasswordRegister);
        registerBtn = (Button) findViewById(R.id.Registerbtn);

    }

    private Boolean existsUser(String lname){
        Cursor data = myDataBaseHelper.getData();
        while(data.moveToNext()){
            if(lastName.equals(data.getString(2))){
                return true;
            }
        }
        return false;
    }
}