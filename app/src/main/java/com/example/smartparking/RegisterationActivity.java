package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegisterationActivity extends AppCompatActivity {
    private EditText firstName, lastName, age, address, cardNumber, securityCode, userPassword, nameOCard, postalCode;
    private Button registerBtn;
    DataBaseHelper myDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
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
                    int cardNo = Integer.parseInt(cardNumber.getText().toString());
                    int sc = Integer.parseInt(securityCode.getText().toString());
                    int age_ = Integer.parseInt(age.getText().toString());
                    String addr_ = address.getText().toString();
                    String nameOnC = nameOCard.getText().toString();
                    int zip = Integer.parseInt(postalCode.getText().toString());
                    //adding to on device store
                    addData(fname,lname,upass,dtime,age_, addr_);

                    //adding to global store
                    CardInfo c1 = new CardInfo(cardNo, sc,nameOnC, zip);
                    Users u1 = new Users(fname+" "+lname,addr_,age_,c1);

                    // some logic to store this object


                    firstName.setText("");
                    lastName.setText("");
                    userPassword.setText("");
                    address.setText("");
                    age.setText("");
                    cardNumber.setText("");
                    securityCode.setText("");



                    Intent intent = new Intent(RegisterationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addData(String fname, String lname, String pass, String dtime, int a, String addr){
        Boolean check = false;
        if(a >=18){
           check = myDataBaseHelper.addData(fname,lname,pass,dtime,a, addr);
        }
        if(check) {
            Toast.makeText(this, "User Registration Successful", Toast.LENGTH_SHORT).show();
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