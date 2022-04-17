package com.example.smartparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.smartparking.data.api.APIInterface;
import com.example.smartparking.data.model.GetParkingByLotIdRequest;
import com.example.smartparking.data.model.GetUserResponse;
import com.example.smartparking.data.model.Location;
import com.example.smartparking.data.model.ParkingLotPojo;
import com.example.smartparking.data.model.SpotsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRecommendationActivity extends AppCompatActivity {
    private TextView infoLots;
    private Button backbtn;
    private Location userLoc;
    APIInterface apiInterface;
    GetUserResponse user1;
    List <ParkingLotPojo> pLotObjList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_recommendation);
        setupRegister();

        user1 = (GetUserResponse) getIntent().getSerializableExtra("userObj");

        // get all parkingLot objects from the server
        Call<List<ParkingLotPojo>> call = apiInterface.getParkingLots();
        call.enqueue(new Callback<List<ParkingLotPojo>>() {
            @Override
            public void onResponse(Call<List<ParkingLotPojo>> call, Response<List<ParkingLotPojo>> response) {
                pLotObjList = response.body();
                String overall = "";
                String tt = "****************************\n";
                for(ParkingLotPojo obj: pLotObjList){
                    int[] arr = avalSpots(obj);
                    double dist = userLoc.getDistance(userLoc,obj.getLocation());
                    String info = "Distance from current location: "+dist +" miles\n";
                    info += "Spots Available: "+arr[0]+"/"+arr[1]+"\n";
                    info += "Parking Lot Name: "+obj.getLocation().getAddress()+"\n";
                    info += "Parking Lot Id: "+obj.getLotId()+"\n";
                    overall+=info;
                    overall+=tt;
                }
                infoLots.setText(overall);
            }

            @Override
            public void onFailure(Call<List<ParkingLotPojo>> call, Throwable t) {

            }
        });
    }

    private int[] avalSpots(ParkingLotPojo obj){
        int i=0;
        int total = 0;
        for(SpotsItem sIt: obj.getSpots()){
            if(sIt.isStatus()) {
                i += 1;
            }
            total +=1;
        }

        return new int[]{i,total};
    }
    private void setupRegister(){
        infoLots = (TextView) findViewById(R.id.tvLocations);
        userLoc = new Location("Golf Performance Center Tempe",33.427876,-111.923934);
        backbtn = (Button) findViewById(R.id.btnback2);
    }
}