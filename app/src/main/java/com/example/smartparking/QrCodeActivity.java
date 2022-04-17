package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.data.api.APIClient;
import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GenericResponse;
import com.example.smartparking.data.model.GetUserByNameRequest;
import com.example.smartparking.data.model.GetUserResponse;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrCodeActivity extends AppCompatActivity {


    private Button directions;
    private Button back;
    private Button generate;
    private EditText pId;
    private ImageView qrImage;
    private TextView onscreeenMsg;
    APIInterface apiInterface;
    GetUserResponse user1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        setupRegister();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        user1 = (GetUserResponse) getIntent().getSerializableExtra("userObj");
        name = user1.getName();
        if(user1.getSpotId()==0){
            directions.setEnabled(false);
        }

        if(user1.getBookCheck()){
            generate.setEnabled(false);
            Qrcode qrObj = new Qrcode(user1.getName(), user1.getBookedOn(),user1.getParkingLotId());
            qrImage.setImageBitmap(qrObj.getQrCode());
        }

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user1.setBookedOn(new Date().toString());
                String parkingLotID = pId.getText().toString();
                user1.setParkingLotId(parkingLotID);
                user1.setBookCheck(true);

                Qrcode qrObj = new Qrcode(user1.getName(), user1.getBookedOn(),user1.getParkingLotId());

                qrImage.setImageBitmap(qrObj.getQrCode());
                generate.setEnabled(false);
                pId.setEnabled(false);

                Call<GenericResponse> call2 = apiInterface.updateUser(user1);
                call2.enqueue(new Callback<GenericResponse>() {
                    @Override
                    public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                        Toast.makeText(QrCodeActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(Call<GenericResponse> call, Throwable t) {

                    }
                });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrCodeActivity.this, DashboardActivity.class);
                intent.putExtra("name",user1.getName());
                startActivity(intent);
            }
        });

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QrCodeActivity.this, DirectionsActivity.class);
                intent.putExtra("userObj",user1);
                startActivity(intent);
            }
        });

        //Declare the timer
        Timer t = new Timer();
//Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {

                                  @Override
                                  public void run() {
                                      //Called each time when 1000 milliseconds (1 second) (the period parameter)
                                      //put your code here
                                      qrCodeValidated();

                                  }

                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                4000,
//Set the amount of time between each execution (in milliseconds)
                3000);
    }



    private void qrCodeValidated(){
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // get user object from global store through node.js & npm server

        GetUserByNameRequest user = new GetUserByNameRequest(name);
        Call<GetUserResponse> call = apiInterface.getUserByName(user);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                user1 = response.body();
                if(user1.getSpotId() != 0){
                    onscreeenMsg.setText("User validated, Assigned spot at Parking Lot: "+user1.getParkingLotId()+"\n Spot Id: "+user1.getSpotId());
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {

            }
        });
    }

    private void setupRegister(){
        directions = (Button) findViewById(R.id.btnNext);
        generate = (Button) findViewById(R.id.generateQrcode);
        back = (Button) findViewById(R.id.btnDash1);
        pId = (EditText) findViewById(R.id.ptLotId);
        qrImage = (ImageView) findViewById(R.id.QrCodeView);
        onscreeenMsg = (TextView) findViewById(R.id.textViewMsg);
    }
}