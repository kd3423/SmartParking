package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DataBaseHelper myDataBaseHelper;
    private EditText eUsername;
    private EditText ePassword;
    private Button eLogin;
    public static String user_logged;
    boolean credFlag = false;
    String tempName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDataBaseHelper = new DataBaseHelper(this);

        setupRegister();

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Gson gson = new Gson();
//                Users u1 = gson.fromJson(getIntent().getStringExtra("user_obj"), Users.class);

                String inputUserName = eUsername.getText().toString();
                user_logged = inputUserName;
                System.out.println(user_logged+" IS a USER");
                String inputPassword = ePassword.getText().toString();
                if(inputUserName.isEmpty() || inputPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Enter a valid username or password!", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean correctCred = existsUser(inputUserName,inputPassword);
                    if(!correctCred){
                        Toast.makeText(LoginActivity.this,"Invalid username or password!", Toast.LENGTH_SHORT).show();
                    }
                    else{





                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.putExtra("name", tempName);
                        Toast.makeText(LoginActivity.this,"Successfully Logged-In", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private void setupRegister(){
        eUsername = (EditText) findViewById(R.id.ptLotId);
        ePassword = (EditText) findViewById(R.id.etPassword);
        eLogin = (Button) findViewById(R.id.loginbtn);

    }

    private Boolean existsUser(String lname,String password){
        String cond = "last_name=" +lname +" AND "+"password="+password;
        Cursor data = myDataBaseHelper.getData();
        while(data.moveToNext()){
            if(lname.equals(data.getString(2)) && password.equals(data.getString(3))){
                this.tempName = data.getString(1)+" "+data.getString(2);
                return true;
            }
        }
        return false;
    }

    public String getUser_logged(){
        return user_logged;
    }

}