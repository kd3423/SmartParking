package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartparking.data.api.APIClient;
import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GetParkingByLotIdRequest;
import com.example.smartparking.data.model.GetUserByNameRequest;
import com.example.smartparking.data.model.GetUserResponse;
import com.example.smartparking.data.model.ParkingLotPojo;
import com.example.smartparking.data.model.SpotsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DirectionsActivity extends AppCompatActivity {
    private TextView dir1;
    private TextView dir2;
    private TextView dir3;
    private TextView dir4;

    private Button btn;
    APIInterface apiInterface;
    GetUserResponse user1;
    ParkingLotPojo pLotObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions);
        setupRegister();

        apiInterface = APIClient.getClient().create(APIInterface.class);

        user1 = (GetUserResponse) getIntent().getSerializableExtra("userObj");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DirectionsActivity.this, DashboardActivity.class);
                intent.putExtra("name",user1.getName());
                startActivity(intent);
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

        // get parkingLot object from global store through node.js & npm server

        GetParkingByLotIdRequest parkingLot = new GetParkingByLotIdRequest(user1.getParkingLotId());
        Call<ParkingLotPojo> call = apiInterface.getParkingByLotId(parkingLot);
        call.enqueue(new Callback<ParkingLotPojo>() {
            @Override
            public void onResponse(Call<ParkingLotPojo> call, Response<ParkingLotPojo> response) {
                pLotObj = response.body();
                for(SpotsItem spObj: pLotObj.getSpots()){
                    if(spObj.getId() == user1.getSpotId()){
                        List<String> directions = spObj.getDirections();
                        dir1.setText(directions.get(0));
                        dir2.setText(directions.get(1));
                        dir3.setText(directions.get(2));
                        dir4.setText(directions.get(3));
                    }
                }
            }

            @Override
            public void onFailure(Call<ParkingLotPojo> call, Throwable t) {

            }
        });


    }
    private void setupRegister(){
        dir1 = (TextView) findViewById(R.id.tvDir1);
        dir2 = (TextView) findViewById(R.id.tvDir2);
        dir3 = (TextView) findViewById(R.id.tvDir3);
        dir4 = (TextView) findViewById(R.id.tvDir4);
        btn = (Button) findViewById(R.id.btnDash2);

    }
}