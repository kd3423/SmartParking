package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.data.api.APIClient;
import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GenericResponse;
import com.example.smartparking.data.model.GetUserResponse;

import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);
        setupRegister();

        apiInterface = APIClient.getClient().create(APIInterface.class);


        user1 = (GetUserResponse) getIntent().getSerializableExtra("userObj");


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
    }

//    private void startTimerThread() {
//        Handler handler = new Handler();
//        Runnable runnable = new Runnable() {
//            private long startTime = System.currentTimeMillis();
//            public void run() {
//                while (user1.getSpotId()!=0) {
//                    try {
//                        Thread.sleep(1000);
//                    }
//                    catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    handler.post(new Runnable(){
//                        public void run() {
//                            tvTime.setText("" + ((System.currentTimeMillis() - this.startTime) / 1000));
//                        }
//                    });
//                }
//            }
//        };
//        new Thread(runnable).start();
//    }

    private void setupRegister(){
        directions = (Button) findViewById(R.id.btnNext);
        generate = (Button) findViewById(R.id.generateQrcode);
        back = (Button) findViewById(R.id.btnDash1);
        pId = (EditText) findViewById(R.id.ptLotId);
        qrImage = (ImageView) findViewById(R.id.QrCodeView);
        onscreeenMsg = (TextView) findViewById(R.id.textViewMsg);
    }
}