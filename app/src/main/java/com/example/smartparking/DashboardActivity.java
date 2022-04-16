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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {
    private TextView welcome;
    private TextView history;
    private Button reserve;
    private Button onSite;
    private Button myQrcode;
    private Button searchLots;
    APIInterface apiInterface;
    GetUserResponse user1;

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
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {

            }
        });

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
                double lat = 33.435303;
                double lon = 111.929483;
                intent.putExtra("Ulat",lat);
                intent.putExtra("Ulon",lon);
                intent.putExtra("Uplace","Ten01");
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

    }

    private void setupRegister(){
        welcome = (TextView) findViewById(R.id.textView5);
        searchLots = (Button) findViewById(R.id.searchLotsBtn);
        history = (TextView) findViewById(R.id.historytv);
        reserve = (Button) findViewById(R.id.reserve);
        onSite = (Button) findViewById(R.id.Onsitebtn);
        myQrcode = (Button) findViewById(R.id.Getqrcode);
    }
}