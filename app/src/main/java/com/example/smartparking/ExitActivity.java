package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GenericResponse;
import com.example.smartparking.data.model.GetParkingByLotIdRequest;
import com.example.smartparking.data.model.GetUserResponse;
import com.example.smartparking.data.model.ParkingLotPojo;
import com.example.smartparking.data.model.SpotsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExitActivity extends AppCompatActivity {
    private TextView exitText;
    private TextView note;
    private TextView back;
    APIInterface apiInterface;
    GetUserResponse user1;
    ParkingLotPojo pLotObj;
    double bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        setupRegister();

        user1 = (GetUserResponse) getIntent().getSerializableExtra("userObj");
        bill = getIntent().getDoubleExtra("bill",0.0);
        note.setEnabled(false);
        back.setEnabled(false);
        // validate the payment
        ChargePayment processPaymentObj = new ChargePayment(user1,bill);

        if(processPaymentObj.getPaymentStatus()){
            String cardNo = Long.toString(user1.getCard().getCardNumber());
            exitText.setText("Payment successful for: $"+Double.toString(bill)+"\n with the registered card ending with "+cardNo.substring(cardNo.length()-5));
            exitText.setEnabled(true);


            // first get the parkingLot object and make changes to it and then update the parkingLot object on server
            GetParkingByLotIdRequest parkingLot = new GetParkingByLotIdRequest(user1.getParkingLotId());
            Call<ParkingLotPojo> call = apiInterface.getParkingByLotId(parkingLot);
            call.enqueue(new Callback<ParkingLotPojo>() {
                @Override
                public void onResponse(Call<ParkingLotPojo> call, Response<ParkingLotPojo> response) {
                    pLotObj = response.body();
                    for(SpotsItem spObj: pLotObj.getSpots()){
                        if(spObj.getId() == user1.getSpotId()){
                            spObj.setStatus(true);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ParkingLotPojo> call, Throwable t) {

                }
            });

            // update parkingLot object
            Call<GenericResponse> call2 = apiInterface.updateParkingLot(pLotObj);
            call2.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                    Toast.makeText(ExitActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<GenericResponse> call, Throwable t) {

                }
            });


            // update the user object
            user1.setBookedOn("");
            user1.setParkingLotId("");
            user1.setSpotId(0);
            user1.setBookCheck(false);
            call2 = apiInterface.updateUser(user1);
            call2.enqueue(new Callback<GenericResponse>() {
                @Override
                public void onResponse(Call<GenericResponse> call, Response<GenericResponse> response) {
                    Toast.makeText(ExitActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFailure(Call<GenericResponse> call, Throwable t) {

                }
            });
            back.setEnabled(true);

        }
        else{
            exitText.setText("Payment cannot be processed, please try again!");
            exitText.setEnabled(true);
            back.setEnabled(true);
        }

    }

    private void setupRegister(){
        exitText = (TextView) findViewById(R.id.tvPayment);
        note = (TextView) findViewById(R.id.textView4);
        back = (Button) findViewById(R.id.btnDash);
    }
}