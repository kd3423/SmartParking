package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartparking.data.api.APIClient;
import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GetUserByNameRequest;
import com.example.smartparking.data.model.GetUserResponse;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private TextView welcome;
    private TextView history;
    private Button reserve;
    private Button onSite;
    private Button myQrcode;
    private Button endSession;
    private Button searchLots;
    private TextView costInfoTv;
    APIInterface apiInterface;
    double bill;

    GetUserResponse user1;
    boolean temp = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupRegister();

        String name = getIntent().getStringExtra("name");
        apiInterface = APIClient.getClient().create(APIInterface.class);

        // get user object from global store through node.js & npm server

        GetUserByNameRequest user = new GetUserByNameRequest(name);
        Call<GetUserResponse> call = apiInterface.getUserByName(user);
        call.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                user1 = response.body();
                if(!user1.getBookCheck()){
                    myQrcode.setEnabled(false);
                }
                else{
                    reserve.setEnabled(false);
                    onSite.setEnabled(false);
                }
                welcome.setText("Welcome "+ user1.getName());
                history.setText(!TextUtils.isEmpty(user1.getHistory()) ? user1.getHistory() : "No record found!");
                temp = true;
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {

            }
        });
        endSession.setEnabled(false);
        myQrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, QrCodeActivity.class);
                intent.putExtra("userObj",user1);
                startActivity(intent);
            }
        });

        searchLots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, LocationRecommendationActivity.class);
                intent.putExtra("userObj",user1);
                startActivity(intent);
            }
        });

        onSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, QrCodeActivity.class);
                intent.putExtra("userObj",user1);
                startActivity(intent);
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, QrCodeActivity.class);
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
                                      updateCurrentCost();

                                  }

                              },
//Set how long before to start calling the TimerTask (in milliseconds)
                4000,
//Set the amount of time between each execution (in milliseconds)
                3000);

        endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCurrentCost();
                Intent intent = new Intent(DashboardActivity.this, ExitActivity.class);
                intent.putExtra("userObj",user1);
                intent.putExtra("bill",bill);
                startActivity(intent);

            }
        });

    }

    private void updateCurrentCost() {
        if (temp) {
            if (user1.getSpotId() != 0) {
                CostEstimator estimator = new CostEstimator(user1.getBookedOn(), true);
                bill = estimator.getBillAmount();
                String costInfo = "PakingLot Id: " + user1.getParkingLotId() + "\n";
                costInfo += "Spot Id: " + user1.getSpotId() + "\n";
                costInfo = "Cost accumulated: $" + bill + "\n";
                costInfo += "Time Spent: " + estimator.getMins() + " mins\n";
                costInfoTv.setText(costInfo);
                endSession.setEnabled(true);
            } else {
                costInfoTv.setText("No Booking yet!");
            }
        }
    }

    private void setupRegister(){
        welcome = (TextView) findViewById(R.id.textView5);
        searchLots = (Button) findViewById(R.id.searchLotsBtn);
        history = (TextView) findViewById(R.id.historytv);
        reserve = (Button) findViewById(R.id.reserve);
        onSite = (Button) findViewById(R.id.Onsitebtn);
        myQrcode = (Button) findViewById(R.id.Getqrcode);
        endSession = (Button) findViewById(R.id.btnExit);
        costInfoTv = (TextView) findViewById(R.id.tvCost);
    }
}